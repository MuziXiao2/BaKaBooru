import { defineStore } from 'pinia'
import type { AtlasTreeOption } from '@/types/upload'
import type Image from '@/types/image'

export const useUploadStateStore = defineStore('upload-state', {
  state: () => ({
    uploadedImages: [] as Array<Image>,

    data: [
      {
        title: 'Atlas 1',
        key: 'atlas1',
        type: 'Atlas',
        children: [
          { title: 'Image 1-1', key: 'image1-1', type: 'Image' },
          { title: 'Image 1-2', key: 'image1-2', type: 'Image' },
        ],
      },
      {
        title: 'Atlas 2',
        key: 'atlas2',
        type: 'Atlas',
        children: [],
      },
      {
        title: 'Atlas 3',
        key: 'atlas3',
        type: 'Atlas',
        children: [
          { title: 'Image 3-1', key: 'image3-1', type: 'Image' },
          { title: 'Image 3-2', key: 'image3-2', type: 'Image' },
        ],
      },
    ] as Array<AtlasTreeOption>,

    expandedKeys: [] as Array<string>,
    checkedKeys: [] as Array<string>,
  }),
  getters: {},
  actions: {
    addData: (title: string) => {
      store.data.push({
        title: title,
        key: 'a',
        type: 'Atlas',
        children: [{ title: title, key: 'b', type: 'Image' }],
      })
    },
  },
})
