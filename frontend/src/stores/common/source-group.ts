import { defineStore } from 'pinia'
import { getSourceGroups } from '@/api/source-group.ts'
import type SourceGroup from '@/types/source-group'

export const useSourceGroupStore = defineStore('source-group', {
  state: () => ({
    sourceGroups: [] as SourceGroup[],
    isSourceGroupsLoaded: false as boolean,
  }),
  getters: {},
  actions: {
    async fetchSourceGroups() {
      const response = await getSourceGroups()
      this.sourceGroups = response.data
      this.isSourceGroupsLoaded = true
    },
  },
})
