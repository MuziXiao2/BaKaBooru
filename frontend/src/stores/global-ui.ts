import { defineStore } from 'pinia'

export const useGlobalUiStore = defineStore('global-ui', {
  state: () => ({
    isDarkMode: false,
    message: {
      visible: false,
      content: 'Test',
      type: 'info',
      duration: 1000,
    },
  }),
  actions: {
    toggleDarkMode() {
      this.isDarkMode = !this.isDarkMode
    },
    showMessage(content: string) {
      this.message.visible = true
      this.message.content = content
    },
    hideMessage() {
      this.message.visible = false
    },
  },
})
