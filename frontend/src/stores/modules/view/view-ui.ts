import { defineStore } from 'pinia'
import type { ContextMenu } from '@/types/global'
import type Atlas from '@/types/atlas'

export const useViewUiStore = defineStore('view-ui', {
  state: () => ({
    isLoading: false,

    isSiderCollapsed: false,

    showViewAtlas: false,

    contextMenu: {
      visible: false,
      x: 0,
      y: 0,
      atlas: null as Atlas | null,
    } as ContextMenu,
  }),
  getters: {},
  actions: {
    startLoading() {
      this.isLoading = true
    },

    stopLoading() {
      this.isLoading = false
    },

    openViewAtlas() {
      this.showViewAtlas = true
    },

    closeViewAtlas() {
      this.showViewAtlas = false
    },

    openContextMenu(x: number, y: number, atlas: Atlas) {
      this.contextMenu.visible = true
      this.contextMenu.x = x
      this.contextMenu.y = y
      this.contextMenu.atlas = atlas
    },

    closeContextMenu() {
      this.contextMenu.visible = false
    },
  },
})
