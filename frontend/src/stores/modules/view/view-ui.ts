import { defineStore } from 'pinia'
import type Atlas from '@/types/atlas'
import { useImageStore } from '@/stores/common/image.ts'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'

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
    },
  }),
  getters: {},
  actions: {
    startLoading() {
      this.isLoading = true
    },

    stopLoading() {
      this.isLoading = false
    },

    async openViewAtlas(atlas: Atlas) {
      const imageStore = useImageStore()
      const viewStateStore = useViewStateStore()

      viewStateStore.setCurrentAtlas(atlas)
      await imageStore.fetchImages()
      useViewStateStore().setCurrentImage(imageStore.images[0])
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
