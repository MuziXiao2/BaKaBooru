import { defineStore } from 'pinia'
import { getAllAtlases } from '@/api'
import type { Atlas, Source } from '@/types'

export const useAtlasStore = defineStore('atlas', {
  state: () => ({
    atlases: [] as Atlas[],
    isAtlasesLoaded: false,
  }),
  getters: {},
  actions: {
    async update(source: Source) {
      this.isAtlasesLoaded = false
      if (!source) return
      this.atlases = await getAllAtlases(source.id)
      this.isAtlasesLoaded = true
    },
  },
})
