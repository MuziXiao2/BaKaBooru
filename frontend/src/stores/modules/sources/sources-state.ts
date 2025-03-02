import { defineStore } from 'pinia'
import type { MenuOption } from 'naive-ui'
import { renderIcon } from '@/utils/icon.ts'
import { InformationCircleOutline as TestIcon } from '@vicons/ionicons5'

export const useSourcesStateStore = defineStore('sources-state', {
  state: () => ({
    currentSourceGroup: null as SourceGroup | null,
  }),
  getters: {
    // 菜单选项
    options(): Array<MenuOption> {
      const soucreGroupOptions: Array<MenuOption> = []

      // 将图源添加到对应type下的children
      useSourceGroupStore().sourceGroups.forEach((sourceGroup) => {
        soucreGroupOptions.push({
          key: `${sourceGroup.sn}`,
          label: sourceGroup.name,
          icon: renderIcon(TestIcon),
          show: true,
          disabled: false,
        })
      })

      return soucreGroupOptions
    },
  },
  actions: {
    setCurrentGroup() {},
  },
})
