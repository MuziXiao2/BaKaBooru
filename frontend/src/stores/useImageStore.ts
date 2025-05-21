import { defineStore } from 'pinia'
import { getImage, getImageFileUrl, queryImages } from '@/api'
import pLimit from 'p-limit'
import type { ImageDetail, ImageItem } from '@/types'

// 搜索表单接口
interface SearchForm {
  keyword: string
  tag: string
  createdAt: [string, string] | []
  updatedAt: [string, string] | []
  sortBy: 'title' | 'createdAt' | 'updatedAt'
  sortDirection: 'asc' | 'desc'
}

export const useImageStore = defineStore('imageSearch', {
  state: () => ({
    form: {
      keyword: '',
      tag: '',
      createdAt: [] as [string, string] | [],
      updatedAt: [] as [string, string] | [],
      sortBy: 'title',
      sortDirection: 'asc',
    } as SearchForm,
    page: 1,
    pageSize: 20,
    loading: false,
    error: null as string | null,
    noMoreData: false,
    images: [] as ImageItem[],
    selectedImage: null as ImageItem | null,
    selectedImageDetails: null as ImageDetail | null,
  }),

  actions: {
    async fetchImages() {
      this.loading = true
      this.error = null

      try {
        const res = await queryImages({
          keyword: this.form.keyword,
          tags: this.form.tag,
          sortBy: this.form.sortBy,
          sortDirection: this.form.sortDirection,
          page: this.page,
          size: this.pageSize,
        })

        this.noMoreData = res.content.length === 0 || this.page >= res.totalPages

        // 限制并发请求（最多 5 个同时请求）
        const limit = pLimit(5)
        this.images = await Promise.all(
          res.content.map((img) =>
            limit(async () => ({
              uuid: img.uuid,
              title: img.title,
              url: await getImageFileUrl(img.coverHash),
            })),
          ),
        )
      } catch (e) {
        this.error = e instanceof Error ? e.message : '加载图片失败'
        this.images = []
        console.error('加载图片失败:', e)
      } finally {
        this.loading = false
      }
    },

    /**
     * 设置目标页面
     * @param targetPage 目标页码
     */
    goToPage(targetPage: number) {
      if (targetPage < 1 || this.loading) return
      this.page = targetPage
    },

    /**
     * 设置每页显示数量
     * @param size 每页图片数量
     */
    setPageSize(size: number) {
      if (this.loading || size < 1) return
      this.pageSize = size
      this.page = 1 // 重置到第一页
      this.noMoreData = false
      this.images = [] // 清空图片以避免部分页面显示
    },

    /**
     * 重置搜索表单和分页状态
     */
    resetForm() {
      this.form = {
        keyword: '',
        tag: '',
        createdAt: [],
        updatedAt: [],
        sortBy: 'title',
        sortDirection: 'asc',
      }
      this.page = 1
      this.pageSize = 20 // 重置到默认值
      this.noMoreData = false
      this.images = []
    },

    async setSelectedImage(image: ImageItem | null) {
      this.loading = true
      this.error = null
      this.selectedImage = image
      if (image) {
        try {
          this.selectedImageDetails = await getImage(image.uuid)
        } catch (e) {
          this.error = e instanceof Error ? e.message : '加载图片详情失败'
          this.selectedImageDetails = null
          console.error('加载图片详情失败:', e)
        } finally {
          this.loading = false
        }
      } else this.selectedImageDetails = null
    },

    updateImageTitle(uuid: string, title: string) {
      const img = this.images.find((i) => i.uuid === uuid)
      if (img) img.title = title
      if (this.selectedImageDetails && this.selectedImageDetails.uuid === uuid) {
        this.selectedImageDetails.title = title
      }
    },

    /**
     * 清除错误状态
     */
    clearError() {
      this.error = null
    },
  },
})
