<script setup lang="ts">
import { nextTick, toRefs } from 'vue'
import { useViewUiStore } from '@/stores'
import type Atlas from '@/types/atlas'

const props = defineProps<{ atlas: Atlas }>()
const { atlas } = toRefs(props)

const viewUiStore = useViewUiStore()

const options = [
  { label: '查看', key: 'view' },
  { label: '分享', key: 'share' },
  { type: 'divider', key: 'd1' },
  { label: '移动', key: 'move' },
  { label: '复制', key: 'copy' },
  { type: 'divider', key: 'd2' },
  { label: '编辑', key: 'rename' },
  { label: '删除', key: 'delete' },
]

const handleContextMenu = (e: MouseEvent) => {
  e.preventDefault()
  viewUiStore.closeContextMenu()
  nextTick().then(() => {
    setContextMenu.openContextMenu(e.clientX, e.clientY, atlas.value)
  })
}

const handleSelect = (key: string) => {
  switch (key) {
    case 'view':
      viewUiStore.viewAtlas(atlas.value)
      break
    case 'share':
      viewUiStore.shareAtlas(atlas.value)
      break
    case 'move':
      viewUiStore.moveAtlas(atlas.value)
      break
    case 'copy':
      viewUiStore.copyAtlas(atlas.value)
      break
    case 'rename':
      viewUiStore.renameAtlas(atlas.value)
      break
    case 'delete':
      viewUiStore.deleteAtlas(atlas.value)
      break
  }
  viewUiStore.closeContextMenu()
}

const onClickOutside = () => {}
</script>

<template>
  <div @contextmenu="handleContextMenu">
    <slot />
  </div>
  <n-dropdown
    placement="bottom-start"
    trigger="manual"
    :show="viewUiStore.contextMenu.visible"
    :x="viewUiStore.contextMenu.x"
    :y="viewUiStore.contextMenu.y"
    :options="options"
    @clickoutside="onClickOutside"
    @select="handleSelect"
  />
</template>

<style scoped></style>
