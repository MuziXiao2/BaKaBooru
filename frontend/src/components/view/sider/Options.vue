<script setup lang="ts">
import { onMounted } from 'vue'
import { useViewUiStore } from '@/stores/modules/view.ts'

const viewUiStore = useViewUiStore()

// 加载图源
onMounted(async () => {
  viewUiStore.startLoading()
  await viewUiStore.fetchSources()
  viewUiStore.stopLoading()
})

const handleMenuUpdate = async (key: string) => {
  viewUiStore.startLoading()

  viewUiStore.atlases = []
  viewUiStore.isAtlasesLoaded = false

  if (key === 'default') {
    viewUiStore.setCurrentSource(null)
  } else {
    const selectedSource = viewUiStore.sources.find((source) => `${source.id}` === key) || null
    viewUiStore.setCurrentSource(selectedSource)
    await viewUiStore.fetchAtlases()
  }

  viewUiStore.stopLoading()
}
</script>

<template>
  <n-card id="source-list" size="small" content-style="padding: 5px;">
    <n-menu
      v-if="viewUiStore.isSourcesLoaded"
      :options="viewUiStore.options"
      :default-value="'default'"
      :root-indent="10"
      :indent="0"
      @update:value="handleMenuUpdate"
    >
    </n-menu>
  </n-card>
</template>

<style scoped>
#source-list {
  border: 1px solid gray;
  border-radius: 10px;
}
</style>
