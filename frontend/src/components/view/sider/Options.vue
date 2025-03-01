<script setup lang="ts">
import { onMounted } from 'vue'
import { useViewUiStore } from '@/stores/modules/view/view-ui.ts'
import { useSoucreStore } from '@/stores/common/source.ts'
import { useAtlasStore } from '@/stores/common/atlas.ts'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'

const viewUiStore = useViewUiStore()
const viewStateStore = useViewStateStore()
const sourceStore = useSoucreStore()
const atlasStore = useAtlasStore()

// 加载图源
onMounted(async () => {
  viewUiStore.startLoading()
  await sourceStore.fetchSources()
  viewUiStore.stopLoading()
})

const handleMenuUpdate = async (key: string) => {
  viewUiStore.startLoading()

  atlasStore.atlases = []
  atlasStore.isAtlasesLoaded = false

  if (key === 'default') {
    viewStateStore.setCurrentSource(null)
  } else {
    const selectedSource = sourceStore.sources.find((source) => `${source.id}` === key) || null
    viewStateStore.setCurrentSource(selectedSource)
    await atlasStore.fetchAtlases()
  }

  viewUiStore.stopLoading()
}
</script>

<template>
  <n-card id="options" size="small" title="图源">
    <n-menu
      v-if="sourceStore.isSourcesLoaded"
      :options="viewStateStore.options"
      :default-value="'default'"
      :root-indent="10"
      :indent="0"
      @update:value="handleMenuUpdate"
    >
    </n-menu>
  </n-card>
</template>

<style scoped>
#options {
}
</style>
