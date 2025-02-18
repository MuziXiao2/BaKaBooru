import { defineStore } from 'pinia'
import { useSourceStore } from '@/stores/sourceStore.ts'
import { type MenuOption, NIcon } from 'naive-ui'
import { type Component, h } from 'vue'
import {
  InformationCircleOutline as TestIcon,
  HomeOutline as HomeIcon,
  StarOutline as StarIcon
} from '@vicons/ionicons5'
import type { MenuGroupOptionMap } from '@/types/Option'
import type Source from '@/types/source'
import { RouterLink } from 'vue-router'


function renderIcon(icon: Component) {
  return () => h(NIcon, null, { default: () => h(icon) })
}

export const useSiderStore = defineStore('sider', {
  state: () => ({

  }),
  getters: {
    // 菜单选项
    options: (state) => {
      const sourceStore = useSourceStore()
      const sources: Source[] = sourceStore.sources

      // 默认选项
      const defaultOption: MenuOption = {
        key: 'default',
        label: () =>
          h(
            RouterLink,
            {
              to: { name: 'default' }
            },
            { default: () => '主页' }
          ),
        icon: renderIcon(HomeIcon),
        show: true
      }

      // 图源选项
      const sourceOption: MenuGroupOptionMap = {
        local: {
          type: 'group',
          label: '本地图源',
          key: 'local',
          show: true,
          children: []
        },
        network: {
          type: 'group',
          label: '网络图源',
          key: 'network',
          show: true,
          children: []
        }
      }

      // 将图源添加到对应type下的children
      sources.forEach(source => {
        sourceOption[source.type].children.push({
          key: `${source.id}`,
          label: () =>
            h(
              RouterLink,
              {
                to: { name: 'atlases', params: { id: source.id } }
              },
              { default: () => source.name }
            ),
          icon: renderIcon(TestIcon),
          show: true,
          disabled: false
        })
      })

      // 其他选项
      const otherOption: MenuOption[] = [
        {
          key: 'divider',
          type: 'divider',
          props: {
            style: {
              marginLeft: '32px'
            }
          }
        },
        {
          label: '收藏',
          key: 'favorite',
          icon: renderIcon(StarIcon)
        }
      ]

      return [defaultOption, sourceOption.local, sourceOption.network, ...otherOption]
    }
  },
  actions: {

  }
})
