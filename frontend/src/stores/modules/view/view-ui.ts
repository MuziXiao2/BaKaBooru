import { defineStore } from 'pinia'
import type Atlas from '@/types/atlas'
import { useImageStore } from '@/stores/common/image.ts'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'
import { h } from 'vue'
import type { ModalApiInjection } from 'naive-ui/es/modal/src/ModalProvider'
import type { Component } from 'vue'

export const useViewUiStore = defineStore('view-ui', {
  state: () => ({
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
    async openViewAtlas(modal: ModalApiInjection, component: Component) {
      const imageStore = useImageStore()
      await imageStore.fetchImages()
      useViewStateStore().setCurrentImage(imageStore.images[0])

      const m = modal.create({
        preset: 'card',
        content: () => h(component),
        closable: false,
        contentStyle: 'padding: 0;',
      })

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
