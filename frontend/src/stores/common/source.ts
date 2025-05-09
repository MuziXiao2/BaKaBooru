import { defineStore } from 'pinia'
import { getAllSources } from '@/api'
import type { Source } from '@/types'

export const useSoucreStore = defineStore('source', {
  state: () => ({
    isSourcesLoaded: false,
    sources: [] as Source[],
  }),
  getters: {},
  actions: {
    async update() {
      this.isSourcesLoaded = false
      this.sources = await getAllSources()
      this.isSourcesLoaded = true
    },
  },
})
