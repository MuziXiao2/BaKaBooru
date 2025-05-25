import { defineStore } from 'pinia'
import { getFileDetails, getImage, getImageFileUrl } from '@/api'
import type { ImageItem, ImageDetail, FileDetail } from '@/types'

export const useCurrentImageStore = defineStore('currentImage', {
  state: () => ({
    currentImageItem: null as ImageItem | null,
    currentImageDetail: null as ImageDetail | null,
    currentFileDetails: [] as FileDetail[],
    currentFileIndex: 0 as number,
    currentFileDetail: null as FileDetail | null,
    currentFileUrl: null as string | null,
    loading: false,
    error: null as string | null,
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

    // 是否有错误
    hasError: (state) => state.error !== null,
  },

  actions: {
    // 设置当前图片
    async setCurrentImage(imageItem: ImageItem | null) {
      this.loading = true
      this.error = null

      try {
        if (!imageItem) {
          this.resetState()
          return
        }

        // 先重置当前状态
        this.resetState()
        this.currentImageItem = imageItem

        // 并行获取所有需要的数据
        const [imageDetail, fileDetails] = await Promise.all([
          getImage(imageItem.uuid),
          getFileDetails(imageItem.uuid),
        ])

        // 设置图片详情
        this.currentImageDetail = imageDetail
        this.currentFileDetails = fileDetails

        if (fileDetails.length > 0) {
          // 设置第一个文件为当前文件
          this.currentFileIndex = 0
          this.currentFileDetail = fileDetails[0]

          // 获取文件URL
          this.currentFileUrl = await getImageFileUrl(this.currentFileDetail.hash)
        }
      } catch (e) {
        this.handleError(e, '加载图片详情失败')
        this.resetState()
      } finally {
        this.loading = false
      }
    },

    // 设置当前文件索引
    async setCurrentFileIndex(index: number) {
      if (index < 0 || index >= this.currentFileDetails.length) {
        return
      }

      this.loading = true
      try {
        this.currentFileIndex = index
        this.currentFileDetail = this.currentFileDetails[index]

        if (this.currentFileDetail) {
          this.currentFileUrl = await getImageFileUrl(this.currentFileDetail.hash)
        }
      } catch (e) {
        this.handleError(e, '切换图片失败')
      } finally {
        this.loading = false
      }
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
      try {
        return await getImageFileUrl(hash)
      } catch (e) {
        throw new Error(e instanceof Error ? e.message : '无法获取文件 URL')
      }
    },

    // 重置状态
    resetState() {
      this.currentImageItem = null
      this.currentImageDetail = null
      this.currentFileDetails = []
      this.currentFileIndex = 0
      this.currentFileDetail = null
      this.currentFileUrl = null
      this.error = null
    },

    // 清除错误
    clearError() {
      this.error = null
    },

    // 处理错误
    handleError(e: unknown, defaultMessage: string) {
      this.error = e instanceof Error ? e.message : defaultMessage
      console.error(`${defaultMessage}:`, e)
    },
  },
})
