<script setup lang="ts">
import { useAtlasStore } from '@/stores/atlasStore.ts'
import AtlasCard from '@/components/AtlasesViews/Atlases/AtlasCard.vue'
import { onMounted, ref } from 'vue'
import type Atlas from '@/types/Atlas'
import { useRoute } from 'vue-router'

const route = useRoute()
const atlasStore = useAtlasStore()

// 加载图集
let atlases: Atlas[] = []
const id = route.params.id as string
const isAtlasesLoaded = ref(false)
onMounted(async () => {
  await atlasStore.fetchAtlases(id)
  isAtlasesLoaded.value = true
  atlases = atlasStore.atlasesMap[id]
  console.log('[BaKaBooru] 图集获取完成')
})

</script>

<template>
  <n-card id="atlases" size="small" bordered>
    <!-- 仅在图集加载完成后显示 -->
    <n-flex v-if="isAtlasesLoaded">
      <AtlasCard
        v-for="(atlas, index) in atlases"
        :key="index"
        :atlas="atlas"
      />
    </n-flex>
    <!-- 加载时显示旋转动画 -->
    <n-spin v-else />
  </n-card>
</template>

<style scoped>
#atlases {
  height: 100%;

}


</style>
