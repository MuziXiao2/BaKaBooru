import { defineStore } from 'pinia'
import type { GlobalUiState } from '@/stores/types/global'

export const useGlobalUiStore = defineStore('globalUi', {
  state: (): GlobalUiState => ({
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
