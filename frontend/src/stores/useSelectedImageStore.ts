import { defineStore } from 'pinia'
import { getImage, getImageFileUrl } from '@/api'
import type { ImageItem, ImageDetail } from '@/types'

export const useSelectedImageStore = defineStore('selectedImage', {
  state: () => ({
    selectedImageItem: null as ImageItem | null,
    selectedImageDetails: null as ImageDetail | null,
    loading: false,
    error: null as string | null,
  }),

  actions: {
    async setSelectedImage(imageItem: ImageItem | null) {
      this.loading = true
      this.error = null
      this.selectedImageItem = imageItem
      if (imageItem) {
        try {
          this.selectedImageDetails = await getImage(imageItem.uuid)
        } catch (e) {
          this.error = e instanceof Error ? e.message : '加载图片详情失败'
          this.selectedImageDetails = null
          console.error('加载图片详情失败:', e)
        } finally {
          this.loading = false
        }
      } else {
        this.selectedImageDetails = null
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
      if (this.selectedImageDetails && this.selectedImageDetails.uuid === uuid) {
        this.selectedImageDetails.title = title
      }
    },
  },
})
