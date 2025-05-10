import { defineStore } from 'pinia'
import { getAllSources } from '@/api'
import type { Sources } from '@/types'

export const useSoucreStore = defineStore('source', {
  state: () => ({
    isSourcesLoaded: false,
    sources: {} as Sources,
  }),
  getters: {
    getSource: (state) => (id: string) => {
      return state.sources[id] || null
    },
  },
  actions: {
    async update() {
      this.isSourcesLoaded = false
      const sources = await getAllSources()
      this.sources = Object.fromEntries(sources.map((source) => [source.id, source]))
      this.isSourcesLoaded = true
    },
  },
})
