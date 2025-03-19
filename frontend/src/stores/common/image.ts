import { defineStore } from 'pinia'
import { getImages } from '@/api'
import { useViewStateStore } from '@/stores'
import type { Image } from '@/types'

export const useImageStore = defineStore('image', {
  state: () => ({
    images: [] as Image[],
    isImagesLoaded: false,
  }),
  getters: {},
  actions: {
    async fetchImages() {
      const currentSource = useViewStateStore().currentSource
      const currentAtlas = useViewStateStore().currentAtlas

      if (!currentSource || !currentAtlas) return
      const response = await getImages(currentSource, currentAtlas)

      this.images = response.data
      this.isImagesLoaded = true
    },
  },
})
