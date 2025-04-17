import { defineStore } from 'pinia'
import { getAtlases } from '@/api'
import type { Atlas } from '@/types'

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
      this.atlases = await getAtlases(source)
      this.isAtlasesLoaded = true
    },
  },
})
