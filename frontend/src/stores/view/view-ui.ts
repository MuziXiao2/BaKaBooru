import { defineStore } from 'pinia'
import { useImageStore, useModalStore, useViewStateStore } from '@/stores'
import type { ModalApiInjection } from 'naive-ui/es/modal/src/ModalProvider'

export const useViewUiStore = defineStore('view-ui', {
  state: () => ({
    isSiderCollapsed: false,
    contextMenu: {
      visible: false,
      x: 0,
      y: 0,
    },
  }),
  getters: {},
  actions: {
    async openViewAtlasModal(modal: ModalApiInjection) {

      const modalStore = useModalStore()
      modalStore.openModal(modal, 'ViewAtlas')
    },

    closeViewAtlasModal() {
      const modalStore = useModalStore()
      modalStore.closeModal()
    },

    openContextMenu(x: number, y: number) {
      this.contextMenu.visible = true
      this.contextMenu.x = x
      this.contextMenu.y = y
    },

    closeContextMenu() {
      this.contextMenu.visible = false
    },
  },
})
