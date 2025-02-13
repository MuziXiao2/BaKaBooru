<script setup lang="ts">
import { toRefs } from 'vue'
import type { Atlas } from '@/types/atlas'


const props = defineProps<{
  atlas: Atlas
}>()

const { atlas } = toRefs(props)

</script>

<template>
  <n-card
    :title="atlas.name"
  >
    <template #cover>
      <img id="image" :src="atlas.cover_url">
    </template>
  </n-card>
</template>

<style scoped>
.n-card {
  border: 1px solid gray;
  max-width: 200px;
}

#image {
  height: 180px;
  object-fit: contain;
}

</style>
