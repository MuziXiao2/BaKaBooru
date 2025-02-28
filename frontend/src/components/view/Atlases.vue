<script setup lang="ts">
import AtlasCard from '@/components/view/atlases/AtlasCard.vue'
import Default from '@/components/view/atlases/Default.vue'
import { useAtlasStore } from '@/stores/common/atlas.ts'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'

const viewUiStateStore = useViewStateStore()
const atlasStore = useAtlasStore()
</script>

<template>
  <!-- 默认页面 -->
  <default v-if="viewUiStateStore.currentSource === null" />
  <!-- 图集列表 -->
  <n-flex v-else-if="atlasStore.isAtlasesLoaded">
    <AtlasCard v-for="(atlas, index) in atlasStore.atlases" :key="index" :atlas="atlas" />
  </n-flex>
  <!-- 加载图标 -->
  <n-spin v-else />
</template>

<style scoped></style>
