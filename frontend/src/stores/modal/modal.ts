import { defineStore } from 'pinia'
import type { ModalApiInjection } from 'naive-ui/es/modal/src/ModalProvider'
import type { ModalReactive } from 'naive-ui'
import { modalList } from '@/stores/modal/modalList.ts'

export const useModalStore = defineStore('modal', {
  state: () => ({
    currentModal: null as ModalReactive | null,
  }),
  getters: {},
  actions: {
    openModal(modal: ModalApiInjection, name: string) {
      this.currentModal = modal.create(modalList[name])
    },
    closeModal() {
      this.currentModal?.destroy()
    },
  },
})
