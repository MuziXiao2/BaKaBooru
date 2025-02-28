<script setup lang="ts">
import { nextTick, toRefs } from 'vue'
import { useViewUiStore } from '@/stores'
import type Atlas from '@/stores/types/atlas'

const props = defineProps<{ atlas: Atlas }>()
const { atlas } = toRefs(props)

const viewUiStore = useViewUiStore()

const handleSelect = (key: string | number) => {
  if (key === 'show') {
    console.log('666')
  }
  viewUiStore.contextMenu.visible = false
}

const options = [
  { label: '查看', key: 'view' },
  { label: '删除', key: 'delete' },
  { label: '分享', key: 'share' },
  { label: '移动', key: 'move' },
  { label: '复制', key: 'copy' },
  { label: '重命名', key: 'rename' },
]

const handleContextMenu = (e: MouseEvent) => {
  e.preventDefault()
  viewUiStore.contextMenu.visible = false
  nextTick().then(() => {
    viewUiStore.contextMenu.visible = true
    viewUiStore.contextMenu.x = e.clientX
    viewUiStore.contextMenu.y = e.clientY
    viewUiStore.contextMenu.atlas = atlas.value
  })
}

const onClickOutside = () => {
  viewUiStore.contextMenu.visible = false
}
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
    :on-clickoutside="onClickOutside"
    @select="handleSelect"
  />
</template>

<style scoped></style>
