import { defineStore } from 'pinia'

export const useUiStore = defineStore('ui', {
  state: () => ({
    isSiderCollapsed: false,
  }),
  getters: {},
  actions: {},
})
