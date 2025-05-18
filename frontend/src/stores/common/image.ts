import { defineStore } from 'pinia'
import type { Image} from '@/types'

export const useImageStore = defineStore('image', {
  state: () => ({
    images: [] as Image[],
    isImagesLoaded: false,
  }),
  getters: {},
  actions: {

  },
})
