import { defineStore } from 'pinia'
import { getImages } from '@/api'
import type { Atlas, Image } from '@/types'

export const useImageStore = defineStore('image', {
  state: () => ({
    images: [] as Image[],
    isImagesLoaded: false,
  }),
  getters: {},
  actions: {
    async update(source: Source, atlas: Atlas) {
      this.isImagesLoaded = false
      if (!atlas) return
      this.images = await getImages(source, atlas)
      this.isImagesLoaded = true
    },
  },
})
