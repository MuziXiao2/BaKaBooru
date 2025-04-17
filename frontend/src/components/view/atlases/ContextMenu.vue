<script setup lang="ts">
import { useViewUiStore } from '@/stores'
import { useModal } from 'naive-ui'

const modal = useModal()
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

const handleSelect = async (key: string) => {
  viewUiStore.closeContextMenu()

  switch (key) {
    case 'view':
      await viewUiStore.openViewAtlasModal(modal)
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

const handleClickOutside = () => {
  console.log('click outside')
  viewUiStore.closeContextMenu()
}
</script>

<template>
  <n-dropdown
    placement="bottom-start"
    trigger="manual"
    :x="viewUiStore.contextMenu.x"
    :y="viewUiStore.contextMenu.y"
    :options="options"
    :show="viewUiStore.contextMenu.visible"
    @clickoutside="handleClickOutside"
    @select="handleSelect"
  />
</template>

<style scoped></style>
