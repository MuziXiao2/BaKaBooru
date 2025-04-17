import { defineStore } from 'pinia'
import type { ModalApiInjection } from 'naive-ui/es/modal/src/ModalProvider'
import type { ModalReactive } from 'naive-ui'
import { modalList } from '@/stores/modules/modal/modalList.ts'

export const useModalStore = defineStore('modal', {
  state: () => ({
    currentModal: null as ModalReactive | null,
    openedModal: null as string | null,

  }),
  getters: {},
  actions: {
    openModal(modal: ModalApiInjection, name: string) {
      this.currentModal = modal.create(modalList[name])
      this.openedModal = name
    },

    closeModal() {
      this.currentModal?.destroy()
      this.openedModal = null
    },

  },
})
