import { h } from 'vue'
import AddSource from '@/components/modal/view/AddSource.vue'
import ViewAtlas from '@/components/modal/view/ViewAtlas.vue'
import type { ModalProps } from 'naive-ui'

interface ModalConfig extends ModalProps {
  preset: 'card' | 'dialog'
  content: () => ReturnType<typeof h>
  closable: boolean
  contentStyle: string
  style: string
}

interface ModalList {
  [key: string]: ModalConfig
}

export const modalList: ModalList = {
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
}
