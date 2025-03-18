<script setup lang="ts">
import { onMounted } from 'vue'
import { useSoucreStore } from '@/stores/common/source.ts'
import { useAtlasStore } from '@/stores/common/atlas.ts'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'
import type { MenuOption } from 'naive-ui'
import type { Source } from '@/types'
import CreateMenu from '@/components/view/sider/source-list/CreateMenu.vue'

const viewStateStore = useViewStateStore()
const sourceStore = useSoucreStore()
const atlasStore = useAtlasStore()

// 加载图源
onMounted(async () => {
  await sourceStore.fetchGroupsAndSources()
})

const handleMenuUpdate = async (key: string, option: MenuOption) => {
  if (option.key === 'default') {
    viewStateStore.setCurrentSource(null)
  } else {
    const selectedSource =
      sourceStore.sources[option.group as string].find(
        (source: Source) => `${source.sn}` === key,
      ) || null
    viewStateStore.setCurrentSource(selectedSource)
    await atlasStore.fetchAtlases()
  }
}
</script>

<template>
  <n-card id="options" size="small" title="图源">
    <template #header-extra>
      <CreateMenu />
    </template>
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

<style scoped></style>
