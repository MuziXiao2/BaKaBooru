import { defineStore } from 'pinia'
import type { CustomTreeOption } from '@/types/upload'

export const useUploadStateStore = defineStore('upload-state', {
  state: () => ({
    data: [
      {
        label: 'Atlas 1',
        key: 'atlas1',
        type: 'Atlas',
        children: [
          { label: 'Image 1-1', key: 'image1-1', type: 'Image', children: [] },
          { label: 'Image 1-2', key: 'image1-2', type: 'Image', children: [] },
        ],
      },
      { label: 'Atlas 2', key: 'atlas2', type: 'Atlas', children: [] },
      { label: 'Image 3', key: 'image3', type: 'Image', children: [] },
      { label: 'Image 4', key: 'image4', type: 'Image', children: [] },
    ] as Array<CustomTreeOption>,
  }),
  getters: {},
  actions: {
    addData: (newData: CustomTreeOption) => {
      this.data.push(newData)
    },
  },
})
