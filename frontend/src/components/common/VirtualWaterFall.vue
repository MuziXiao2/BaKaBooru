<script setup lang="ts">
import type { Atlas } from '@/types'
import VirtualWaterFall from '@/utils/VirtualWaterFall.vue'
import { computed } from 'vue'
import { useImageStore } from '@/stores'

const props = defineProps<{
  atlases: Atlas[]
  onLeftClick?: (atlas: Atlas) => void
  onRightClick?: (atlas: Atlas, event: MouseEvent) => void
}>()

const imageStore = useImageStore()

const atlasCovers = computed(() =>
  props.atlases.map(async (atlas) => {
    const image = await imageStore.getImage(atlas.sourceUuid, atlas.coverHash)
    return {
      id: atlas.uuid,
      title: image.title,
      url: image.url,
      width: image.width,
      height: image.height,
    }
  }),
)
</script>

<template>
  <VirtualWaterFall :items="atlasCovers">
    <template #item="{ item }">
      <img :src="item.url" :alt="item.title" style="width: 100%; height: 100%" />
    </template>
  </VirtualWaterFall>
</template>

<style scoped></style>
