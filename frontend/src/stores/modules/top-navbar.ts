import { defineStore } from 'pinia'
import type { MenuOption } from '@/types/option'

export const useTopNavbarStore = defineStore('top-navbar', {
  state: () => ({
   options: [
      { label: '浏览', key: 'view' },
      { label: '图源管理', key: 'source' },

    ] as Array<MenuOption>,
  }),
  getters: {


  },
  actions: {},
})
