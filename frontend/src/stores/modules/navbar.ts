import { defineStore } from 'pinia'
import type { MenuOption } from 'naive-ui'

export const useNavbarStore = defineStore('navbar', {
  state: () => ({
   options: [
      { label: '浏览', key: 'view' },
      { label: '图源', key: 'sources' },
      { label: '上传', key: 'upload' },
      { label: '设置', key: 'settings' },

    ] as Array<MenuOption>,
  }),
  getters: {


  },
  actions: {},
})
