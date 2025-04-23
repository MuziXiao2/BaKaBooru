import { defineStore } from 'pinia'
import type { Source, Atlas, Image } from '@/types'

export const useViewStateStore = defineStore('view-state', {
  state: () => ({
    atlases: null as Atlas[] | null,
    currentSource: null as Source | null,
    currentAtlas: null as Atlas | null,
    currentImage: null as Image | null,
  }),
  getters: {},
  actions: {
    setCurrentAtlas(atlas: Atlas) {
      this.currentAtlas = atlas
    },

    setCurrentImage(image: Image) {
      this.currentImage = image
    },
  },
})
