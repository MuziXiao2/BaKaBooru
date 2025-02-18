<script setup lang="ts">
import { useAtlasStore } from '@/stores/atlasStore.ts'
import Atlas from '@/components/AtlasesViews/Atlases/Atlas.vue'
import { computed } from 'vue'

const atlasStore = useAtlasStore()

// 获取图集数据，图集数据会自动根据 selectedSourceId 变化更新
const atlases = computed(() => atlasStore.atlases)

</script>

<template>
  <n-card id="atlases" size="small" bordered>
    <!-- 仅在图集加载完成后显示 -->
    <n-flex v-if="atlases.length > 0">
      <Atlas
        v-for="(atlas, index) in atlases"
        :key="index"
        :atlas="atlas"
      />
    </n-flex>
    <n-spin v-else /> <!-- 加载时显示旋转动画 -->
  </n-card>
</template>

<style scoped>
#atlases {
height: 100%;

}


</style>
