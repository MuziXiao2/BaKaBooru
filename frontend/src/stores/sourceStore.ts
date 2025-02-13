import { defineStore } from 'pinia'
import type { Source } from '@/types/source'
import { getAllSources } from '@/api'


export const useSourceStore = defineStore('source', {
  state: () => ({
    //图源列表
    sources: []
  }),
  getters: {


  },
  actions: {
    async fetchSources() {
      try {
        const response = await getAllSources()
        this.sources = response.data
      } catch (error) {
        console.error('获取图源失败', error)
      }
    }
  }
})
