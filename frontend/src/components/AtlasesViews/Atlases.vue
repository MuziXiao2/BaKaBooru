<script setup lang="ts">
import Atlas from '@/components/AtlasesViews/Atlases/Atlas.vue'
import { useSourceStore } from '@/stores/sourceStore'
import type { Source } from '@/types/source'

const sourceStore = useSourceStore()
const sourceData = sourceStore.getSource as Source
const atlases = sourceData.atlases


</script>

<template>
  <n-card
    id="atlases"
    size="small"
  >
    <n-flex >
      <Atlas v-for="(atlas,index) in atlases" :key="index" :atlas="atlas" />
    </n-flex>

  </n-card>
</template>

<style scoped>
#atlases {

}


</style>
