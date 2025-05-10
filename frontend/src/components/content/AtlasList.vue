<script setup lang="ts">
import { useModal } from 'naive-ui'
import { useUiStore, useStateStore, useAtlasStore } from '@/stores'
import VirtualWaterFall from '@/components/common/VirtualWaterFall.vue'
import { onMounted } from 'vue'

const modal = useModal()
const uiStore = useUiStore()
const stateStore = useStateStore()
const atlasStore = useAtlasStore()

onMounted(async () => await atlasStore.update(stateStore.currentSource.id))
</script>

<template>
  <VirtualWaterFall />
</template>

<style scoped></style>
