import { defineStore } from 'pinia'

export const useUploadStateStore = defineStore('upload-state', {
  state: () => ({
    uploadedListData: [] as Array<{
      key: number
      title: string
      images: Array<{ url: string; size: number }>
    }>,
  }),
  getters: {
    isUploadedListEmpty: () => this.uploadedListData.length === 0,
  },
  actions: {
    addUpdataedData(title, url, size) {
      this.uploadedListData.push({
        key: this.uploadedListData.length,
        title: title,
        images: [
          {
            url: url,
            size: size,
          },
        ],
      })
    },
  },
})
