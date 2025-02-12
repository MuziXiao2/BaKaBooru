<script setup lang="ts">
import type { MenuOption } from 'naive-ui'
import { h } from 'vue'
import {
  InformationCircleOutline as TestIcon,
  StarOutline as StarIcon
} from '@vicons/ionicons5'
import type { Component } from 'vue'
import { NIcon } from 'naive-ui'
import { useSourceStore } from '@/stores/sourceStore'

function renderIcon(icon: Component) {
  return () => h(NIcon, null, { default: () => h(icon) })
}

const menuOptions: MenuOption[] = [
  {
    type: 'group',
    label: '本地图源',
    key: 'local-source',
    children: [
      {
        label: 'TEST',
        key: 'test1',
        icon: renderIcon(TestIcon)
      }
    ]
  },
  {
    type: 'group',
    label: '网络图源',
    key: 'network-source',
    children: [
      {
        label: 'TEST',
        key: 'test2',
        icon: renderIcon(TestIcon)
      }
    ]
  },
  {
    key: 'divider-1',
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
  },
  {
    label: 'TEST',
    key: 'test3',
    icon: renderIcon(TestIcon)
  }
]

const sourceStore = useSourceStore()

const handleMenuSelect = (key: string) => {
  sourceStore.setSelectedSource(key)
}

</script>
<template>
  <n-card
    id="source-list"
    size="small"
    title="图源"
  >
    <n-menu
      :options="menuOptions"
      :root-indent="0"
      :indent="10"
      @update:value="handleMenuSelect"
    >
    </n-menu>
  </n-card>
</template>

<style scoped>
#source-list {
  border: 1px solid gray;
  border-radius: 10px;
}


</style>
