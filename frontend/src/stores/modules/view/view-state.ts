import { defineStore } from 'pinia'
import type { Source, Atlas, Image } from '@/types'
import type { MenuOption, ModalReactive } from 'naive-ui'
import { h } from 'vue'
import { NButton } from 'naive-ui'
import { renderIcon } from '@/utils/icon.ts'
import { HomeOutline as HomeIcon, InformationCircleOutline as TestIcon } from '@vicons/ionicons5'
import { useSoucreStore } from '@/stores'

export const useViewStateStore = defineStore('view-state', {
  state: () => ({
    currentSource: null as Source | null,
    currentAtlas: null as Atlas | null,
    currentImage: null as Image | null,
    currentModal: null as ModalReactive | null,
  }),
  getters: {
    // 菜单选项
    options(): Array<MenuOption> {
      // 默认选项
      const defaultOption: MenuOption = {
        key: 'default',
        label: () =>
          h(
            NButton,
            {
              text: true,
            },
            { default: () => '主页' },
          ),
        icon: renderIcon(HomeIcon),
        show: true,
      }

      const groupOptions: Array<MenuOption> = []

      const soucreStore = useSoucreStore()

      soucreStore.groups.forEach((group) => {
        const groupOption = {
          type: 'group',
          label: `${group.name}`,
          key: `${group.id}`,
          show: true,
          children: [] as Array<MenuOption>,
        }

        soucreStore.sources[group.id].forEach((source) => {
          const sourceOption: MenuOption = {
            key: `${source.id}`,
            group: `${group.id}`,
            label: () =>
              h(
                NButton,
                {
                  text: true,
                },
                { default: () => `${source.name}` },
              ),
            icon: renderIcon(TestIcon),
            show: true,
          }

          groupOption.children.push(sourceOption)
        })

        groupOptions.push(groupOption)
      })

      return [defaultOption, ...groupOptions]
    },
  },
  actions: {
    setCurrentSource(source: Source | null) {
      this.currentSource = source
    },

    setCurrentAtlas(atlas: Atlas) {
      this.currentAtlas = atlas
    },

    setCurrentImage(image: Image) {
      this.currentImage = image
    },

    setCurrentModal(modal: ModalReactive) {
      this.currentModal = modal
    },
  },
})
