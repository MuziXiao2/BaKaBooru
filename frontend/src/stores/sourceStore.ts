import { defineStore } from 'pinia'
import DataTest1 from '../../public/data-test1.ts'
import type { Source } from '@/types/source'


export const useSourceStore = defineStore('source', {
  state: () => ({
    //正在选中图源的key
    selectedSource: ''

  }),
  getters: {
    getSource: (state): Source | undefined => {
      return DataTest1 as Source
    }
  },
  actions: {
    setSelectedSource(source: string) {
      this.selectedSource = source
    }
  }
})
