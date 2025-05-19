import { defineStore } from 'pinia'
import { getImageFileUrl, queryImages } from '@/api'
import pLimit from 'p-limit'

// 图片数据接口
export interface ImageItem {
  uuid: string
  title: string
  url: string
}

// 搜索表单接口
interface SearchForm {
  keyword: string
  tag: string
  grade: string
  createTime: [string, string] | [] // 明确为日期范围或空数组
  sort: string
}

export const useImageSearchStore = defineStore('imageSearch', {
  state: () => ({
    form: {
      keyword: '',
      tag: '',
      grade: '',
      createTime: [] as [string, string] | [],
      sort: 'titleMatch',
    } as SearchForm,
    page: 1,
    pageSize: 20,
    loading: false,
    error: null as string | null,
    noMoreData: false,
    images: [] as ImageItem[],
  }),

  actions: {
    /**
     * 获取当前页的图片数据
     */
    async fetchImages() {
      this.loading = true
      this.error = null

      try {
        const res = await queryImages({
          ...this.form,
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
        grade: '',
        createTime: [],
        sort: '',
      }
      this.page = 1
      this.pageSize = 20 // 重置到默认值
      this.noMoreData = false
      this.images = []
    },

    /**
     * 清除错误状态
     */
    clearError() {
      this.error = null
    },
  },
})
