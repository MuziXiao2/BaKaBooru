import { defineStore } from 'pinia'

export const useUploadStateStore = defineStore('upload-state', {
  state: () => ({
    columns: [
      {
        title: '图集标题',
        key: 'title',
      },
      {
        title: '包含图片',
        key: 'images',
      },
      {
        key: 'expand',
      },
    ],

    data: [] as Array<{
      sn: number
      title: string
      images: Array<{ url: string; size: number }>
    }>,
  }),
  getters: {},
  actions: {
    createData(title, url, size) {
      this.data.push({
        id: this.data.length,
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
