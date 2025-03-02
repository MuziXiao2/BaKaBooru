import { defineStore } from 'pinia'
import type Source from '@/types/source'
import type Image from '@/types/image'
import type Atlas from '@/types/atlas'
import type { MenuOption } from 'naive-ui'

import { h } from 'vue'
import { NButton } from 'naive-ui'
import { renderIcon } from '@/utils/icon.ts'
import { HomeOutline as HomeIcon, InformationCircleOutline as TestIcon } from '@vicons/ionicons5'
import { useSoucreStore } from '@/stores/common/source.ts'

export const useViewStateStore = defineStore('view-state', {
  state: () => ({
    currentSource: null as Source | null,
    currentAtlas: null as Atlas | null,
    currentImage: null as Image | null,
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

      const sourceGroupOptions = []

      const soucreStore = useSoucreStore()

      soucreStore.sourceGroups.forEach((sourceGroup) => {
        const sourceGroupOption = {
          type: 'group',
          label: `${sourceGroup.name}`,
          key: `${sourceGroup.id}`,
          show: true,
          children: [],
        }

        soucreStore.sources[sourceGroup.id].forEach((source) => {
          const sourceOption: MenuOption = {
            key: `${source.id}`,
            group: `${sourceGroup.id}`,
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

          sourceGroupOption.children.push(sourceOption)
        })

        sourceGroupOptions.push(sourceGroupOption)
      })

      return [defaultOption, ...sourceGroupOptions]
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
  },
})
