<script setup lang="ts">
import { nextTick } from 'vue'
import { useViewUiStore } from '@/stores'
import type Atlas from '@/types/atlas'

const props = defineProps<{ atlas: Atlas }>()

const viewUiStore = useViewUiStore()

const options = [
  { label: '查看', key: 'view' },
  { label: '分享', key: 'share' },
  { type: 'divider', key: 'd1' },
  { label: '移动', key: 'move' },
  { label: '复制', key: 'copy' },
  { type: 'divider', key: 'd2' },
  { label: '编辑', key: 'edit' },
  { label: '删除', key: 'delete' },
]

const handleContextMenu = (e: MouseEvent) => {
  viewUiStore.closeContextMenu()
  nextTick().then(() => {
    viewUiStore.openContextMenu(e.clientX, e.clientY, props.atlas)
  })
}

const handleSelect = (key: string) => {
  viewUiStore.closeContextMenu()

  switch (key) {
    case 'view':
      viewUiStore.openViewAtlas(props.atlas)
      break
    case 'share':
      break
    case 'move':
      break
    case 'copy':
      break
    case 'edit':
      break
    case 'delete':
      break
  }
}
</script>

<template>
  <div @contextmenu="handleContextMenu">
    <slot />
  </div>
  <n-dropdown
    placement="bottom-start"
    trigger="manual"
    :x="viewUiStore.contextMenu.x"
    :y="viewUiStore.contextMenu.y"
    :options="options"
    :show="viewUiStore.contextMenu.visible"
    @select="handleSelect"
  />
</template>

<style scoped></style>
