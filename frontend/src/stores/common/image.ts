import { defineStore } from 'pinia'
import { getAllImages, getImage } from '@/api'
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
      this.images = await getAllImages(source.uuid, atlas.uuid)
      this.isImagesLoaded = true
    },
    async getImage(sourceUuid: string, imageHash: string): Promise<Image> {
      return await getImage(sourceUuid, imageHash)
    },
  },
})
