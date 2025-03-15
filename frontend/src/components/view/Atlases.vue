<script setup lang="ts">
import AtlasCard from '@/components/view/atlases/AtlasCard.vue'
import Default from '@/components/view/atlases/Default.vue'
import { useAtlasStore } from '@/stores/common/atlas.ts'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'
import ContextMenu from '@/components/view/atlases/atlas-card/ContextMenu.vue'

const viewUiStateStore = useViewStateStore()
const atlasStore = useAtlasStore()
</script>

<template>
  <!-- 默认页面 -->
  <default v-if="viewUiStateStore.currentSource === null" />
  <!-- 图集列表 -->
  <n-flex v-else-if="atlasStore.isAtlasesLoaded" justify="center">
    <atlas-card v-for="(atlas, index) in atlasStore.atlases" :key="index" :atlas="atlas" />
  </n-flex>
  <!-- 加载图标 -->
  <n-spin v-else />
  <!-- 右键菜单 -->
  <context-menu />
</template>

<style scoped></style>
