import { defineStore } from 'pinia'
import { type MenuOption } from 'naive-ui'

export const useUploadStateStore = defineStore('upload-state', {
  state: () => ({}),
  getters: {
    options(): Array<MenuOption> {



      return []
    },
  },
  actions: {},
})
