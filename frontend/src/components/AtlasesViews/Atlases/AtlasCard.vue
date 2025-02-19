<script setup lang="ts">
import { toRefs } from 'vue'
import Atlas from '@/types/Atlas'

const props = defineProps<{ atlas: Atlas }>()
const { atlas } = toRefs(props)

</script>

<template>
  <n-card
    footer-style="padding: 0; height:30px; "
  >
    <template #cover>
      <img id="cover" :src="atlas.cover_url" alt="img">
    </template>
    <template #footer>
      <div id="text"> {{ atlas.name }}</div>
    </template>
  </n-card>
</template>

<style scoped>
.n-card {
  width: 200px;
  height: 230px;
}

#cover {
  width: 200px;
  height: 200px;
  object-fit: scale-down;
}

#text {
  font-size: 20px;
  color: gray;
}


</style>
