<script setup lang="ts">
import Search from '@/components/view/sider/Search.vue'
import SourceList from '@/components/common/container/sourceMetas-container/SourceList.vue'
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

onMounted(async () => {
  await sourceStore.update()
})

const defaultOption: MenuOption = {
  key: 'default',
  label: () => h(NButton, { text: true }, { default: () => '主页' }),
  icon: renderIcon(HomeIcon),
  show: true,
}

const handleSelected = async (sourceMeta: Source) => {
  viewStateStore.currentSource = sourceMeta
  await atlasStore.update(sourceMeta)
  viewStateStore.atlases = atlasStore.atlases
}
</script>

<template>
  <sourceMeta-list
    title="图源"
    size="small"
    :groups="sourceStore.groups"
    :sourceMetas="sourceStore.sourceMetas"
    :is-loaded="sourceStore.isSourcesLoaded"
    :default-option="defaultOption"
    :on-selected="handleSelected"
  >
    <template #extra>
      <create-menu />
    </template>
  </sourceMeta-list>
  <search />
</template>

<style scoped></style>
