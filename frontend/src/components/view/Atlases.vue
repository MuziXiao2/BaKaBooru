<script setup lang="ts">
import { nextTick } from 'vue'
import type { Atlas } from '@/types'
import { useModal } from 'naive-ui'
import { useViewUiStore, useViewStateStore } from '@/stores'
import DefaultPage from '@/components/view/atlases/DefaultPage.vue'
import ContextMenu from '@/components/view/atlases/ContextMenu.vue'
import AtlasCardList from '@/components/common/container/atlases-container/AtlasCardList.vue'

const modal = useModal()
const viewUiStore = useViewUiStore()
const viewStateStore = useViewStateStore()

async function handleLeftClick(atlas: Atlas) {
  viewStateStore.setCurrentAtlas(atlas)
  await viewUiStore.openViewAtlasModal(modal)
}

function handleRightClick(atlas: Atlas, event: MouseEvent) {
  viewStateStore.setCurrentAtlas(atlas)
  event.preventDefault()
  viewUiStore.closeContextMenu()
  nextTick().then(() => viewUiStore.openContextMenu(event.clientX, event.clientY))
}
</script>

<template>
  <!-- 默认页面 -->
  <default-page v-if="viewStateStore.currentSource === null" />
  <!-- 加载图标 -->
  <n-spin v-else-if="viewStateStore.atlases === null" />
  <!-- 图集列表 -->
  <atlas-card-list
    v-else
    :sourceMeta="viewStateStore.currentSource"
    :atlases="viewStateStore.atlases"
    :on-left-click="handleLeftClick"
    :on-right-click="handleRightClick"
  />
  <!-- 右键菜单 -->
  <context-menu />
</template>

<style scoped></style>
