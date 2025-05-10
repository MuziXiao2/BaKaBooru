import { defineStore } from 'pinia'
import type { Source } from '@/types'

export const useStateStore = defineStore('state', {
  state: () => ({
    selectedKey: 'home' as string,
    currentSource: null as Source,
  }),
  getters: {},
  actions: {},
})
