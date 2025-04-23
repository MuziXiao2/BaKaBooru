import { defineStore } from 'pinia'
import type { Tag } from '@/types'

export const useTagStore = defineStore('tag', {
  state: () => ({
    tags: [] as Tag[],
    isTagsLoaded: false,
  }),
  getters: {},
  actions: {
    async update() {
      this.isTagsLoaded = false
      this.tags = await getTags()
      this.isTagsLoaded = true
    },
  },
})
