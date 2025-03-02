<script setup lang="ts">
import { onMounted } from 'vue'
import { useSourcesStateStore } from '@/stores/modules/sources/sources-state.ts'
import { useSourcesUiStore } from '@/stores/modules/sources/sources-ui.ts'

const sourcesUiStore = useSourcesUiStore()
const sourceSourcesStateStore = useSourcesStateStore()

onMounted(async () => {
  sourcesUiStore.startLoading()
  await sourceGroupStore.fetchSourceGroups()
  sourcesUiStore.stopLoading()
})

function handleUpdateValue(key: string) {
  const selectedSourceGroup =
    sourceGroupStore.sourceGroups.find((sourceGroup) => `${sourceGroup.sn}` === key) || null
  sourceSourcesStateStore.setSourceGroup(selectedSourceGroup)
  sourceSourcesStateStore.fetchSources()
}
</script>

<template>
  <n-card size="small" :bordered="false" title="图源组">
    <n-menu
      :options="sourceSourcesStateStore.options"
      @update:value="handleUpdateValue"
      :root-indent="10"
      :indent="0"
    />
  </n-card>
</template>

<style scoped></style>
