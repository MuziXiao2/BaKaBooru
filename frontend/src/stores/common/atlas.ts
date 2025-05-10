import { defineStore } from 'pinia'
import { getAllAtlases } from '@/api'
import type { Atlas } from '@/types'

export const useAtlasStore = defineStore('atlas', {
  state: () => ({
    atlases: [] as Atlas[],
    isAtlasesLoaded: false,
  }),
  getters: {},
  actions: {
    async update(sourceId: string) {
      this.isAtlasesLoaded = false
      if (!sourceId) return
      this.atlases = await getAllAtlases(sourceId)
      this.isAtlasesLoaded = true
    },
  },
})
