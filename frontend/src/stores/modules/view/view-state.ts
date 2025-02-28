import { defineStore } from 'pinia'
import type Source from '@/types/source'
import type Image from '@/types/image'
import type Atlas from '@/types/atlas'
import type { MenuDividerOption, MenuGroupOption, MenuOption } from '@/types/Option'
import { h } from 'vue'
import { NButton } from 'naive-ui'
import { renderIcon } from '@/utils/icon.ts'
import {
  HomeOutline as HomeIcon,
  InformationCircleOutline as TestIcon,
  StarOutline as StarIcon,
} from '@vicons/ionicons5'
import { useSoucreStore } from '@/stores/common/source.ts'

export const useViewStateStore = defineStore('view-state', {
  state: () => ({
    currentSource: null as Source | null,
    currentAtlas: null as Atlas | null,
    currentImage: null as Image | null,

    contextMenu: {
      visible: false as boolean,
      x: 0 as number,
      y: 0 as number,
      atlas: null as Atlas | null,
    },
  }),
  getters: {
    // 菜单选项
    options(): Array<MenuOption | MenuGroupOption | MenuDividerOption> {
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

      // 图源选项
      const sourceOption: { [key: string]: MenuGroupOption } = {
        local: {
          type: 'group',
          label: '本地图源',
          key: 'local',
          show: true,
          children: [],
        },
        network: {
          type: 'group',
          label: '网络图源',
          key: 'network',
          show: true,
          children: [],
        },
      }

      // 将图源添加到对应type下的children
      useSoucreStore().sources.forEach((source) => {
        sourceOption[source.type].children.push({
          key: `${source.id}`,
          label: () =>
            h(
              NButton,
              {
                text: true,
              },
              { default: () => source.name },
            ),
          icon: renderIcon(TestIcon),
          show: true,
          disabled: false,
        })
      })

      // 其他选项
      const otherOption: Array<MenuDividerOption | MenuOption> = [
        {
          key: 'divider',
          type: 'divider',
          props: {
            style: {
              marginLeft: '32px',
            },
          },
        },
        {
          label: '收藏',
          key: 'favorite',
          icon: renderIcon(StarIcon),
        },
      ]

      return [defaultOption, sourceOption.local, sourceOption.network, ...otherOption]
    },
  },
  actions: {
    setCurrentSource(source: Source) {
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
