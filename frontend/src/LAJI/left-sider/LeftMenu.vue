<script setup lang="ts">
import { computed, onMounted } from 'vue'
import { useSoucreStore, useStateStore } from '@/stores'
import type { Source } from '@/types'
import { FolderOutline, HomeOutline, InformationCircleOutline } from '@vicons/ionicons5'
import { createMenuDividerOption, createMenuOption } from '@/LAJI/createMenuOption.ts'

const stateStore = useStateStore()
const sourceStore = useSoucreStore()

onMounted(async () => await sourceStore.update())

const dividerOption = createMenuDividerOption('divider')

const homePageOption = createMenuOption('home', '主页', HomeOutline)

const sourceOption = computed(() => {
  const option = createMenuOption('sources', '图源', InformationCircleOutline)
  option.children = Object.values(sourceStore.sources).map((source: Source) =>
    createMenuOption(source.id, source.name, FolderOutline),
  )
  return option
})

const otherOption = createMenuOption('other', '其他', InformationCircleOutline)

const handleSelected = async (key: string) => {
  stateStore.selectedKey = key
  stateStore.currentSource = sourceStore.getSource(key)
}
</script>

<template>
  <n-menu
    :options="[homePageOption, dividerOption, sourceOption, dividerOption, otherOption]"
    :root-indent="20"
    :indent="16"
    :collapsed-icon-size="20"
    :collapsed-width="64"
    :collapsed="false"
    :default-expand-all="true"
    :value="stateStore.selectedKey"
    @update:value="handleSelected"
  />
</template>

<style scoped>
:deep(.n-menu) {
  background-color: #1f1f1f;
  color: #e0e0e0;
}

:deep(.n-menu-item-content) {
  font-size: 14px;
}
</style>
