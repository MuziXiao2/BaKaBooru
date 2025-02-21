<script setup lang="ts">
import { useAtlasStore } from '@/stores/atlasStore.ts'
import AtlasCard from '@/components/AtlasesViews/AtlasList/AtlasCard.vue'
import { onMounted, ref, watch } from 'vue'
import type Atlas from '@/types/Atlas'
import { useRoute } from 'vue-router'
import AtlasInfo from '@/components/AtlasesViews/AtlasList/AtlasInfo.vue'

const route = useRoute()
const atlasStore = useAtlasStore()

let atlases = []
const sourceId = ref<string>()
const isAtlasesLoaded = ref(false)

async function loadAtlases() {
  //获取图源id
  sourceId.value = route.params.id
  //从后端获取图集列表
  await atlasStore.fetchAtlases(sourceId.value)
  //将图集列表存入atlases
  atlases = atlasStore.atlasesMap[sourceId.value]
  //修改状态
  isAtlasesLoaded.value = true
  console.log('[BaKaBooru] 图集列表加载成功', atlases)
}

// 页面加载时重新加载图集
onMounted(() => loadAtlases())

// 路由变化时重新加载图集
watch(
  () => route.params.id,
  () => {
    isAtlasesLoaded.value = false
    loadAtlases()
  },
)

const showModal = ref(false)
const selectedAtlas = ref<Atlas>()

function handleClick(atlas: Atlas) {
  selectedAtlas.value = atlas
  showModal.value = true
}
</script>

<template>
  <!-- 图集列表 -->
  <n-flex v-if="isAtlasesLoaded">
    <AtlasCard
      v-for="(atlas, index) in atlases"
      :key="index"
      :atlas="atlas"
      @click="handleClick(atlas)"
    />
  </n-flex>
  <!-- 加载图标 -->
  <n-spin v-else />

  <!-- 图集信息 -->
  <n-modal v-model:show="showModal" style="height: 95vh; margin: 0 30px; border-radius: 10px">
    <AtlasInfo v-if="showModal" :atlas="selectedAtlas as Atlas" />
  </n-modal>
</template>

<style scoped></style>
