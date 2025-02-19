<script setup lang="ts">
import { useAtlasStore } from '@/stores/atlasStore.ts'
import AtlasCard from '@/components/AtlasesViews/Atlases/AtlasCard.vue'
import { onMounted, ref, watch } from 'vue'
import type Atlas from '@/types/Atlas'
import { useRoute } from 'vue-router'

const route = useRoute()
const atlasStore = useAtlasStore()

// 加载图集
let atlases: Atlas[] = []
const id = ref(route.params.id as string)
const isAtlasesLoaded = ref(false)

const loadAtlases = async () => {
  await atlasStore.fetchAtlases(id.value)
  isAtlasesLoaded.value = true
  atlases = atlasStore.atlasesMap[id.value]

}

// 页面加载时重新加载图集
onMounted(() => {
  loadAtlases()
  console.log('[BaKaBooru] 图集加载完成', id.value)
})
//路由变化时重新加载图集
watch(() => route.params.id, (newId) => {
  id.value = newId as string
  isAtlasesLoaded.value = false
  loadAtlases()
})

</script>

<template>
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
</template>

<style scoped>



</style>
