import { defineStore } from 'pinia'

export const useSourcesUiStore = defineStore('sources-ui', {
  state: () => ({
    isLoading: false,
    isSiderCollapsed: false,
  }),
  getters: {},
  actions: {
    startLoading() {
      this.isLoading = true
    },

    stopLoading() {
      this.isLoading = false
    },
  },
})
