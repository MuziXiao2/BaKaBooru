import { defineStore } from 'pinia'
import { h } from 'vue'
import ShowOrEdit from '@/components/common/ShowOrEdit.vue'

export const useUploadStateStore = defineStore('upload-state', {
  state: () => ({
    data: [] as Array<{
      key: number
      title: string
      images: Array<{ url: string; size: number }>
    }>,
  }),
  getters: {
    columns: (state) => [
      {
        type: 'selection',
      },
      {
        title: '图集标题',
        key: 'title',
        width: 150,
        render: (row) => {
          const index = state.data.findIndex((item) => item.key === row.key)
          return h(ShowOrEdit, {
            value: row.title,
            onUpdateValue: (v) => (state.data[index].title = v),
          })
        },
      },
      {
        title: '包含图片',
        key: 'images',
      },
    ],
  },
  actions: {


    addData(title, url, size) {
      this.data.push({
        key: this.data.length,
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
