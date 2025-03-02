import { defineStore } from 'pinia'
import { getAtlases } from '@/api/atlas.ts'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'
import type Atlas from '@/types/atlas'

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
