import { defineStore } from 'pinia'
import type Atlas from '@/types/atlas'
import { useImageStore } from '@/stores/common/image.ts'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'
import { h } from 'vue'
import type { ModalApiInjection } from 'naive-ui/es/modal/src/ModalProvider'
import AddSource from '@/components/modal/AddSource.vue'
import ViewAtlas from '@/components/modal/ViewAtlas.vue'
import CreateSourceGroup from '@/components/modal/CreateSourceGroup.vue'

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
     openModal(modal: ModalApiInjection, options) {
      const viewStateStore = useViewStateStore()

      viewStateStore.setCurrentModal(modal.create(options))

      this.showViewAtlas = true
    },


    closeModal() {
      const viewStateStore = useViewStateStore()
      viewStateStore.currentModal?.destroy()
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
