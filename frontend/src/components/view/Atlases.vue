<script setup lang="ts">
import { useViewUiStore } from '@/stores/modules/view.ts'
import AtlasCard from '@/components/view/atlases/AtlasCard.vue'
import type Atlas from '@/stores/types/atlas'
import Default from '@/components/view/atlases/Default.vue'

const viewUiStore = useViewUiStore()

function handleClick(atlas: Atlas) {
  viewUiStore.setCurrentAtlas(atlas)
}
</script>

<template>

  <!-- 默认页面 -->
  <default v-if="viewUiStore.currentSource === null" />
  <!-- 图集列表 -->
  <n-flex v-else-if="viewUiStore.isAtlasesLoaded">
    <AtlasCard
      v-for="(atlas, index) in viewUiStore.atlases"
      :key="index"
      :atlas="atlas"
      @click="handleClick(atlas)"
    />
  </n-flex>
  <!-- 加载图标 -->
  <n-spin v-else />
</template>

<style scoped></style>
