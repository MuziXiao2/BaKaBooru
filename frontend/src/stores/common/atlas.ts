import { defineStore } from 'pinia'
import { getAtlases } from '@/api'
import { useViewStateStore } from '@/stores'
import type { Atlas } from '@/types'

export const useAtlasStore = defineStore('atlas', {
  state: () => ({
    atlases: [] as Atlas[],
    isAtlasesLoaded: false,
  }),
  getters: {},
  actions: {
    async fetchAtlases() {
      const currentSource = useViewStateStore().currentSource
      if (!currentSource) return
      const response = await getAtlases(currentSource.id)
      this.atlases = response.data
      this.isAtlasesLoaded = true
    },
  },
})
