<script setup lang="ts">
import { useStateStore, useAtlasStore } from '@/stores'
import VirtualWaterFall from '@/components/common/VirtualWaterFall.vue'
import { onMounted } from 'vue'
import LoadingSpin from '@/components/common/LoadingSpin.vue'

const stateStore = useStateStore()
const atlasStore = useAtlasStore()

onMounted(async () => await atlasStore.update(stateStore.currentSource.uuid))
</script>

<template>
  <VirtualWaterFall v-if="atlasStore.isAtlasesLoaded" :atlases="atlasStore.atlases" />
  <LoadingSpin v-else />
</template>

<style scoped></style>
