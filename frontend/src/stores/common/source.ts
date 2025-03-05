import { defineStore } from 'pinia'
import type Source from '@/types/source'
import { getSourceGroups, getSources } from '@/api/source.ts'
import type SourceGroup from '@/types/source'

export const useSoucreStore = defineStore('source', {
  state: () => ({
    sourceGroups: [] as SourceGroup[],
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
      return state.sourceGroups.map((sourceGroup) => {
        return {
          label: sourceGroup.name,
          value: sourceGroup.id,
        }
      })
    },
  },
  actions: {
    async fetchSources() {
      let response = await getSourceGroups()

      this.sourceGroups = response.data

      for (const sourceGroup of this.sourceGroups) {
        response = await getSources(sourceGroup)
        this.sources[sourceGroup.id] = response.data
      }

      this.isSourcesLoaded = true
    },
  },
})
