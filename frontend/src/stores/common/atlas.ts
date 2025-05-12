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
    async update(sourceUuid: string) {
      this.isAtlasesLoaded = false
      if (!sourceUuid) return
      this.atlases = await getAllAtlases(sourceUuid)
      this.isAtlasesLoaded = true
    },
  },
})
