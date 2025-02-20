<script setup lang="ts">
import { useAtlasStore } from '@/stores/atlasStore.ts'
import AtlasCard from '@/components/AtlasesViews/Atlases/AtlasCard.vue'
import { onMounted, ref, watch, nextTick } from 'vue'
import type Atlas from '@/types/Atlas'
import { useRoute } from 'vue-router'
import ImageCard from '@/components/AtlasesViews/Atlases/ImageCard.vue'

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


const showModal = ref(false)
const selectedAtlas = ref()

function handleClick(atlas:Atlas) {
  selectedAtlas.value = atlas
  showModal.value = true
}

</script>

<template>
  <n-flex v-if="isAtlasesLoaded">

    <AtlasCard
      v-for="(atlas, index) in atlases"
      :key="index"
      :atlas="atlas"
      @click="handleClick(atlas)"
    />

  </n-flex>
  <n-spin v-else />  <!-- 加载图标 -->

  <n-modal
    v-model:show="showModal"
    style="margin: 0 30px; border-radius: 10px "
  >
    <ImageCard :atlas="selectedAtlas" />
  </n-modal>

</template>

<style scoped>


</style>
