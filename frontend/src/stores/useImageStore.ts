import { defineStore } from 'pinia'
import { getImageFileUrl, queryImages } from '@/api'
import pLimit from 'p-limit'
import type { ImageItem } from '@/types'
import { useSearchFormStore } from '@/stores/useSearchFormStore'
import { usePaginationStore } from '@/stores/usePaginationStore'

export const useImageStore = defineStore('image', {
  state: () => ({
    images: [] as ImageItem[],
    loading: false,
    error: null as string | null,
  }),

  actions: {
    async fetchImages() {
      const searchFormStore = useSearchFormStore()
      const paginationStore = usePaginationStore()

      this.loading = true
      this.error = null

      try {
        const res = await queryImages({
          keyword: searchFormStore.form.keyword,
          tags: searchFormStore.form.tag,
          sortBy: searchFormStore.form.sortBy,
          sortDirection: searchFormStore.form.sortDirection,
          page: paginationStore.page,
          size: paginationStore.pageSize,
        })

        paginationStore.setNoMoreData(res.content.length === 0 || paginationStore.page >= res.totalPages)

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

    updateImageTitle(uuid: string, title: string) {
      const img = this.images.find((i) => i.uuid === uuid)
      if (img) img.title = title
    },

    clearError() {
      this.error = null
    },
  },
})
