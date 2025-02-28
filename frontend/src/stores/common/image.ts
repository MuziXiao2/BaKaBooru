import { defineStore } from 'pinia'
import { getImages } from '@/api'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'
import type Image from '@/types/image'

export const useImageStore = defineStore('image', {
  state: () => ({
    images: [] as Image[],
    isImagesLoaded: false,
  }),
  getters: {},
  actions: {
    async fetchImages() {
      const currentAtlas = useViewStateStore().currentAtlas
      if (!currentAtlas) return
      const response = await getImages(currentAtlas.id)
      this.images = response.data
      this.isImagesLoaded = true
    },
  },
})
