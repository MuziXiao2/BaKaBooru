import { defineStore } from 'pinia'

export const useGlobalUiStore = defineStore('global-ui', {
  state: () => ({
    isLeftMenutoggle: true,
    activePage: 'Gallery',

  }),
  actions: {
    toggleLeftMenu() {
      this.isLeftMenutoggle = !this.isLeftMenutoggle
    },
  },
})
