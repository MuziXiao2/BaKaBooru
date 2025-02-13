<script setup lang="ts">
import Atlas from '@/components/AtlasesViews/Atlases/Atlas.vue'
import { useAtlasStore } from '@/stores/atlasStore.ts'

const atlasStore = useAtlasStore()
const atlases = atlasStore.atlases


</script>

<template>
  <n-card
    id="atlases"
    size="small"
  >
    <n-flex>
      <Atlas v-for="(atlas,index) in atlases" :key="index" :atlas="atlas" />
    </n-flex>

  </n-card>
</template>

<style scoped>
#atlases {

}


</style>
