import { defineStore } from 'pinia'
import type Source from '@/types/source'
import { getSources } from '@/api/source.ts'

export const useSoucreStore = defineStore('source', {
  state: () => ({
    sources: [] as Source[],
    isSourcesLoaded: false,
  }),
  getters: {},
  actions: {
    async fetchSources() {
      const response = await getSources()
      this.sources = response.data
      this.isSourcesLoaded = true
    },
  },
})
