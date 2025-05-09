import { defineStore } from 'pinia'
import { getAllImages } from '@/api'
import type { Atlas, Image, Source } from '@/types'

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
      this.images = await getAllImages(source.id, atlas.uuid)
      this.isImagesLoaded = true
    },
  },
})
