import { defineStore } from 'pinia'
import { getFileDetails, getImage, getImageFileUrl, getTags, updateImageDescription } from '@/api'
import type { ImageDetail, FileDetail } from '@/types'

export const useImageViewerStore = defineStore('currentImage', {
  state: () => ({
    isImageViewerOpen: false,

    currentImageUuid: null as string | null,
    currentFileIndex: 0 as number,
    currentFileUrl: null as string | null,
    currentThumbnailFileUrls: [] as string[],

    currentImageDetail: null as ImageDetail | null,
    currentImageTags: [] as string[],
    currentFileDetails: [] as FileDetail[],
    currentFileDetail: null as FileDetail | null,
  }),

  getters: {
    // 当前文件是否是第一个
    isFirstFile: (state) => state.currentFileIndex === 0,

    // 当前文件是否是最后一个
    isLastFile: (state) => state.currentFileIndex === state.currentFileDetails.length - 1,

    // 获取当前文件
    currentFile: (state): FileDetail | null => {
      if (!state.currentFileDetails.length) return null
      return state.currentFileDetails[state.currentFileIndex] || null
    },

    // 获取总文件数
    totalFiles: (state) => state.currentFileDetails.length,
  },

  actions: {
    // 打开图片信息
    async openImageViewer(uuid: string) {
      if (this.isImageViewerOpen || this.currentImageUuid === uuid) return

      this.currentImageUuid = uuid
      const [imageDetail, imageTags, fileDetails] = await Promise.all([
        getImage(uuid),
        getTags(uuid),
        getFileDetails(uuid),
      ])

      this.currentImageDetail = imageDetail
      this.currentImageTags = imageTags
      this.currentFileDetails = fileDetails
      this.currentFileDetail = fileDetails[0] || null

      for (const file of this.currentFileDetails) {
        const fileUrl = await getImageFileUrl(file.hash)
        this.currentThumbnailFileUrls.push(fileUrl)
      }
      this.currentFileUrl = await getImageFileUrl(this.currentFileDetail.hash)

      this.isImageViewerOpen = true
    },

    closeImageViewer() {
      if (!this.isImageViewerOpen) return

      this.currentImageUuid = null
      this.currentFileIndex = 0
      this.currentFileUrl = null
      this.currentThumbnailFileUrls = []

      this.currentImageDetail = null
      this.currentImageTags = []
      this.currentFileDetails = []
      this.currentFileDetail = null

      this.isImageViewerOpen = false
    },

    // 设置当前文件索引
    async setCurrentFileIndex(index: number) {
      if (index < 0 || index >= this.currentFileDetails.length) return

      this.currentFileIndex = index
      this.currentFileDetail = this.currentFileDetails[index] || null
      this.currentFileUrl = await getImageFileUrl(this.currentFileDetail.hash)
    },

    // 导航到下一个文件
    async nextFile() {
      if (!this.isLastFile) {
        await this.setCurrentFileIndex(this.currentFileIndex + 1)
      }
    },

    // 导航到上一个文件
    async previousFile() {
      if (!this.isFirstFile) {
        await this.setCurrentFileIndex(this.currentFileIndex - 1)
      }
    },

    // 获取图片文件URL
    async getImageFileUrl(hash: string): Promise<string> {
      return await getImageFileUrl(hash)
    },
  },
})
