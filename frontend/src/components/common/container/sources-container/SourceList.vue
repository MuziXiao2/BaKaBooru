<script setup lang="ts">
import { computed, h } from 'vue'
import { type MenuOption, NButton } from 'naive-ui'
import type { Source, Sources } from '@/types'
import LoadingSpin from '@/components/common/LoadingSpin.vue'
import { renderIcon } from '@/utils/icon.ts'
import { InformationCircleOutline as TestIcon } from '@vicons/ionicons5'

const props = defineProps<{
  title: string
  size: string
  isLoaded: boolean
  sources: Sources
  defaultOption: MenuOption
  onSelected?: (source: Source) => void
}>()

const menuOptions = computed(() => {
  const sourceOptions = Object.values(props.sources).map((source: Source) => ({
    key: source.id,
    label: () => h(NButton, { text: true }, { default: () => source.name }),
    icon: renderIcon(TestIcon),
    show: true,
  }))

  return [props.defaultOption, ...sourceOptions]
})

const handleSelected = (key: string) => {
  if (props.onSelected) {
    props.onSelected(props.sources[key] || null)
  }
}
</script>

<template>
  <n-card :size="size" :title="title">
    <template #header-extra>
      <slot name="extra" />
    </template>
    <n-menu
      v-if="isLoaded"
      :options="menuOptions"
      :default-value="'default'"
      :root-indent="10"
      :indent="0"
      @update:value="handleSelected"
    />
    <loading-spin v-else />
  </n-card>
</template>

<style scoped></style>
