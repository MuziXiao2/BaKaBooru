import { defineStore } from 'pinia'
import { useSourceStore } from '@/stores/sourceStore.ts'
import { type MenuOption, NIcon } from 'naive-ui'
import { type Component, h } from 'vue'
import {
  InformationCircleOutline as TestIcon,
  StarOutline as StarIcon
} from '@vicons/ionicons5'


function renderIcon(icon: Component) {
  return () => h(NIcon, null, { default: () => h(icon) })
}


export const useSiderStore = defineStore('sider', {
  state: () => ({
    //正在选中图源的key
    selectedSourceId: 1
  }),
  getters: {
    // 菜单选项
    options: (state) => {
      const sourceStore = useSourceStore()
      const sources = sourceStore.sources
      const sourceOption = {
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
          label: source.name,
          key: `${source.id}`,
          icon: renderIcon(TestIcon),
          show:true,
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

      return [sourceOption.local, sourceOption.network, ...otherOption]
    }
  },
  actions: {
    setSelectedSourceId(id) {
      this.selectedSourceId = id
    }
  }
})
