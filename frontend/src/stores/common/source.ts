import { defineStore } from 'pinia'
import { getGroups, getSourceInfo, getSources } from '@/api'
import type { Group, Source } from '@/types'

export const useSoucreStore = defineStore('source', {
  state: () => ({
    groups: [] as Group[],
    sources: [] as Source[],
    isSourcesLoaded: false,
  }),
  getters: {},
  actions: {
    async update() {
      this.isSourcesLoaded = false
      this.groups = await getGroups()
      this.sources = await getSources()
      for (const source of this.sources) Object.assign(source, await getSourceInfo(source))
      this.isSourcesLoaded = true
    },
  },
})
