<script setup lang="ts">
import Search from '@/components/view/sider/Search.vue'
import SourceList from '@/components/common/container/sources-container/SourceList.vue'
import CreateMenu from '@/components/view/sider/CreateMenu.vue'
import { h, onMounted } from 'vue'
import { useAtlasStore, useSoucreStore, useViewStateStore } from '@/stores'
import { type MenuOption, NButton } from 'naive-ui'
import { renderIcon } from '@/utils/icon.ts'
import { HomeOutline as HomeIcon } from '@vicons/ionicons5'
import type { Source } from '@/types'

const sourceStore = useSoucreStore()
const atlasStore = useAtlasStore()
const viewStateStore = useViewStateStore()

onMounted(async () => await sourceStore.update())

const defaultOption: MenuOption = {
  key: 'default',
  label: () => h(NButton, { text: true }, { default: () => '主页' }),
  icon: renderIcon(HomeIcon),
  show: true,
}

const handleSelected = async (source: Source) => {
  viewStateStore.currentSource = source
  await atlasStore.update(source)
  viewStateStore.atlases = atlasStore.atlases
}
</script>

<template>
  <source-list
    title="图源"
    size="small"
    :is-loaded="sourceStore.isSourcesLoaded"
    :sources="sourceStore.sources"
    :default-option="defaultOption"
    :on-selected="handleSelected"
  >
    <template #extra>
      <create-menu />
    </template>
  </source-list>
  <search />
</template>

<style scoped></style>
