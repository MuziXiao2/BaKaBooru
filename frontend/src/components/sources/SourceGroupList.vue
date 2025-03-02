<script setup lang="ts">
import type { MenuOption } from 'naive-ui'
import { onMounted } from 'vue'
import { useSourcesStateStore } from '@/stores/modules/sources/sources-state.ts'
import { useSourcesUiStore } from '@/stores/modules/sources/sources-ui.ts'
import { useSourceGroupStore } from '@/stores/common/source-group.ts'

const sourcesUiStore = useSourcesUiStore()
const sourceGroupStore = useSourceGroupStore()
const sourceSourcesStateStore = useSourcesStateStore()

onMounted(async () => {
  sourcesUiStore.startLoading()
  await sourceGroupStore.fetchSourceGroups()
  sourcesUiStore.stopLoading()
})

function handleUpdateValue(key: string, item: MenuOption) {
  console.log(key + ' ' + item.label)
}
</script>

<template>
  <n-card size="small">
    <n-menu :options="sourceSourcesStateStore.options" @update:value="handleUpdateValue" />
  </n-card>
</template>

<style scoped></style>
