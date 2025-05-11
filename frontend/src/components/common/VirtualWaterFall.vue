<script setup lang="ts">
import type { Atlas } from '@/types'
import FsVirtualWaterFall from '@/utils/FsVirtualWaterFall/index.vue'

const props = defineProps<{
  atlases: Atlas[]
  onLeftClick?: (atlas: Atlas) => void
  onRightClick?: (atlas: Atlas, event: MouseEvent) => void
}>()
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
