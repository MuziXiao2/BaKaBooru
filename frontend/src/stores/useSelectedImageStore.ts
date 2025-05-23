import { defineStore } from 'pinia'
import { getFileDetails, getImage, getImageFileUrl } from '@/api'
import type { ImageItem, ImageDetail, FileDetail } from '@/types'

export const useCurrentImageStore = defineStore(' currentImage', {
  state: () => ({
    currentImageItem: null as ImageItem | null,
    currentImageDetail: null as ImageDetail | null,
    currentFileDetails: [] as FileDetail[],
    currentFileDetail: null as FileDetail | null,
    loading: false,
    error: null as string | null,
  }),

  actions: {
    async setCurrentImage(imageItem: ImageItem | null) {
      this.loading = true
      this.error = null
      this.currentImageItem = imageItem
      if (imageItem) {
        try {
          this.currentImageDetail = await getImage(imageItem.uuid)
          this.currentFileDetails = await getFileDetails(imageItem.uuid)
          this.currentFileDetail = this.currentFileDetails[0]
        } catch (e) {
          this.error = e instanceof Error ? e.message : '加载图片详情失败'
          this.currentImageDetail = null
          console.error('加载图片详情失败:', e)
        } finally {
          this.loading = false
        }
      } else {
        this.currentImageDetail = null
      }
    },

    async getImageFileUrl(hash: string): Promise<string> {
      try {
        return await getImageFileUrl(hash)
      } catch (e) {
        throw new Error(e instanceof Error ? e.message : '无法获取文件 URL')
      }
    },
  },
})
