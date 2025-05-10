<script setup lang="ts">
import type { Atlas } from '@/types'
import FsVirtualWaterFall from '@/utils/FsVirtualWaterFall/index.vue'

const props = defineProps<{
  atlases: Atlas[]
  onLeftClick?: (atlas: Atlas) => void
  onRightClick?: (atlas: Atlas, event: MouseEvent) => void
}>()
const curl =
  'http://localhost:9000/bakabooru/730e67f1cc507b26b0e20033a4fd9b649606fea5e0bd76cd3cc96d6b7766f798?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ga9t9ShVs41vivkX0zaR%2F20250510%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250510T173537Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=2a92c56ad5157a3354b58d8fb17ae065f230cd8f9ded805d9e8604030b59d048'
const atlasCovers = [
  {
    uuid: 'a',
    url: curl,
    width: 111,
    height: 2223,
  },
]
</script>

<template>
  <FsVirtualWaterFall :items="atlasCovers" :column="4" :gap="20">
    <template #item="{ item }">
      <template #default="{ item }">
        <img :src="item.url" style="width: 100%" />
      </template>
    </template>
  </FsVirtualWaterFall>
</template>

<style scoped></style>
