<script setup lang="ts">
import type { Atlas, Source } from '@/types'
import AtlasCard from '@/components/common/container/atlases-container/AtlasCard.vue'

const props = defineProps<{
  source: Source
  atlases: Atlas[]
  onLeftClick?: (atlas: Atlas) => void
  onRightClick?: (atlas: Atlas, event: MouseEvent) => void
}>()
</script>

<template>
  <n-flex justify="center">
    <atlas-card
      v-for="(atlas, index) in atlases"
      :key="index"
      :atlas="atlas"
      @click="onLeftClick?.(atlas)"
      @contextmenu="(event) => onRightClick?.(atlas, event)"
    />
  </n-flex>
</template>

<style scoped></style>
