import { defineStore } from 'pinia'
import { getGroups, getSources } from '@/api'
import type { Group, Source } from '@/types'

export const useSoucreStore = defineStore('source', {
  state: () => ({
    groups: [] as Group[],
    sources: {} as { [key: string]: Source[] },
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
    groupSelectOptions(state) {
      return state.groups.map((group) => {
        return {
          label: group.name,
          value: group.id,
        }
      })
    },
  },
  actions: {
    async fetchGroupsAndSources() {
      const response = await getGroups()
      this.groups = response.data

      for (const group of this.groups) {
        const response = await getSources(group)
        this.sources[group.id] = response
      }

      this.isSourcesLoaded = true
    },
  },
})
