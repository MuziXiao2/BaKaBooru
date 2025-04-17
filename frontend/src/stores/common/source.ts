import { defineStore } from 'pinia'
import { getGroups, getSources } from '@/api'
import type { Group, Source } from '@/types'

export const useSoucreStore = defineStore('source', {
  state: () => ({
    groups: [] as Group[],
    sources: [] as Source[],
    isSourcesLoaded: false,
  }),
  getters: {
    sourceTypeSelectOptions() {
      return [
        {
          label: 'local',
          value: 'local',
        },
        {
          label: 'network',
          value: 'network',
        },
      ]
    },
  },
  actions: {
    async update() {
      this.isSourcesLoaded = false
      this.groups = await getGroups()
      this.sources = await getSources()
      this.isSourcesLoaded = true
    },
  },
})
