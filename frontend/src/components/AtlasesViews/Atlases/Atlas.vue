<script setup lang="ts">
import { toRefs } from 'vue'
import type { Atlas } from '@/types/source'


const props = defineProps<{
  atlas: Atlas
}>()

const { atlas } = toRefs(props)

</script>

<template>
  <n-card
    :title="'TEST '+atlas.id"
  >
    <template #cover>
      <img id="image" :src="atlas.images[0].url">
    </template>
  </n-card>
</template>

<style scoped>
.n-card {
  border: 1px solid gray;
  max-width: 200px;
}

#image{
  height: 180px;
  object-fit: contain;
}

</style>
