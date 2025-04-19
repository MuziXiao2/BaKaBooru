<script setup lang="ts">
import type { Atlas } from '@/types'

const props = defineProps<{ atlas: Atlas }>()
</script>

<template>
  <n-card footer-style="padding: 0; height:30px;" hover="hover">
    <template #cover>
      <img id="cover" v-if="atlas.coverUrl" :src="atlas.coverUrl" alt="img" />
      <n-empty v-else />
    </template>
    <template #footer>
      <div id="text">{{ atlas.title }}</div>
    </template>
  </n-card>
</template>

<style scoped>
.n-card {
  border-radius: 10px;
  width: 200px;
  height: 230px;
}

.n-card:hover {
  transform: scale(1.02);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

#cover {
  width: 200px;
  height: 200px;
  object-fit: scale-down;
}

#text {
  font-size: 20px;
  color: gray;
  text-align: center;
}
</style>
