import { h } from 'vue'
import { defineStore } from 'pinia'
import { useViewStateStore } from '@/stores'
import AddSource from '@/components/modal/AddSource.vue'
import ViewAtlas from '@/components/modal/ViewAtlas.vue'
import CreateGroup from '@/components/modal/CreateGroup.vue'
import type { Atlas } from '@/types'
import type { ModalApiInjection } from 'naive-ui/es/modal/src/ModalProvider'

export const useViewUiStore = defineStore('view-ui', {
  state: () => ({
    isSiderCollapsed: false,

    openedModal: null as string | null,

    contextMenu: {
      visible: false,
      x: 0,
      y: 0,
      atlas: null as Atlas | null,
    },
  }),
  getters: {},
  actions: {
    openModal(modal: ModalApiInjection, name: string) {
      const viewStateStore = useViewStateStore()

      const options = {
        ViewAtlas: {
          preset: 'card',
          content: () => h(ViewAtlas),
          closable: false,
          contentStyle: 'padding: 0;',
          style: 'width: auto; height: auto;',
        },
        AddSource: {
          preset: 'card',
          content: () => h(AddSource),
          closable: false,
          contentStyle: 'padding: 0;',
          style: 'width: auto; height: auto;',
        },
        CreateGroup: {
          preset: 'card',
          content: () => h(CreateGroup),
          closable: false,
          contentStyle: 'padding: 0;',
          style: 'width: auto; height: auto;',
        },
      }

      viewStateStore.setCurrentModal(modal.create(options[name]))

      this.openedModal = name
    },

    closeModal() {
      const viewStateStore = useViewStateStore()
      viewStateStore.currentModal?.destroy()
      this.openedModal = null
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
