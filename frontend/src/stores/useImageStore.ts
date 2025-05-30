import { defineStore } from 'pinia'
import { getImageFileUrl, queryImages } from '@/api'
import pLimit from 'p-limit'
import type { ImageItem } from '@/types'
import { useQueryFormStore } from '@/stores/useQueryFormStore.ts'

export const useImageStore = defineStore('image', {
  state: () => ({
    images: [] as ImageItem[],

    currentPage: 1,
    currentPageSize: 20,
    totalPages: 1,
    noMoreData: false,

    loading: false,
    error: null as string | null,
  }),

  actions: {
    setPage(targetPage: number) {
      if (targetPage < 1) this.currentPage = 1
      else if (targetPage >= this.totalPages) this.currentPage = this.totalPages
      else this.currentPage = targetPage
    },
    setPageSize(targetPageSize: number) {
      this.currentPageSize = targetPageSize
      this.currentPage = 1
    },
    async queryImages() {
      const searchFormStore = useQueryFormStore()
      this.loading = true
      this.error = null

      try {
        const res = await queryImages({
          keyword: searchFormStore.form.keyword,
          tags: searchFormStore.form.tags,
          sortBy: searchFormStore.form.sortBy,
          sortDirection: searchFormStore.form.sortDirection,
          page: this.currentPage,
          size: this.currentPageSize,
        })

        this.totalPages = res.totalPages
        this.noMoreData = this.currentPage >= this.totalPages

        const limit = pLimit(5)
        this.images = await Promise.all(
          res.content.map((img) =>
            limit(async () => ({
              uuid: img.uuid,
              title: img.title,
              url: await this.getImageFileUrl(img.coverHash),
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

    async getImageFileUrl(hash: string): Promise<string> {
      try {
        return await getImageFileUrl(hash)
      } catch (e) {
        throw new Error(e instanceof Error ? e.message : '无法获取文件 URL')
      }
    },

    clearError() {
      this.error = null
    },
  },
})
