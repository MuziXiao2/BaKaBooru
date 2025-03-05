import { defineStore } from 'pinia'
import type Atlas from '@/types/atlas'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'
import { h } from 'vue'
import type { ModalApiInjection } from 'naive-ui/es/modal/src/ModalProvider'
import AddSource from '@/components/modal/AddSource.vue'
import ViewAtlas from '@/components/modal/ViewAtlas.vue'
import CreateGroup from '@/components/modal/CreateGroup.vue'

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
    openModal(modal: ModalApiInjection, key: string) {
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

      viewStateStore.setCurrentModal(modal.create(options[key]))

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
