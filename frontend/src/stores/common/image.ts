import { defineStore } from 'pinia'
import { getImages } from '@/api'
import type { Atlas, Image, Source } from '@/types'

export const useImageStore = defineStore('image', {
  state: () => ({
    images: [] as Image[],
    isImagesLoaded: false,
  }),
  getters: {},
  actions: {
    async update(source: Source | null, atlas: Atlas | null) {
      this.isImagesLoaded = false
      if (!atlas) return
      this.images = await getImages(source, atlas)
      this.isImagesLoaded = true
    },
  },
})
