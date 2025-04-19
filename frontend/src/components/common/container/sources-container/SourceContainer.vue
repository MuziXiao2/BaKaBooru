<script setup lang="ts">
import { computed, h } from 'vue'
import { type MenuOption, NButton } from 'naive-ui'
import type { Group, Source } from '@/types'
import LoadingSpin from '@/components/common/LoadingSpin.vue'
import { renderIcon } from '@/utils/icon.ts'
import { InformationCircleOutline as TestIcon } from '@vicons/ionicons5'

const props = defineProps<{
  title: string
  size: string
  groups?: Group[]
  sources: Source[]
  isLoaded: boolean
  defaultOption: MenuOption
  onSelected?: (source: Source) => void
}>()

const menuOptions = computed(() => {
  if (props.groups) {
    const groupOptions = props.groups.map((group) => ({
      type: 'group',
      label: group.name,
      key: group.id,
      show: true,
      children:
        props.sources
          .filter((source) => source.groupId === group.id)
          ?.map((source) => ({
            key: source.id,
            group: group.id,
            label: () => h(NButton, { text: true }, { default: () => source.name }),
            icon: renderIcon(TestIcon),
            show: true,
          })) || [],
    }))
    return [props.defaultOption, ...groupOptions]
  } else {
    const sourceOptions =
      props.sources?.map((source) => ({
        key: source.sn,
        label: () => h(NButton, { text: true }, { default: () => source.name }),
        icon: renderIcon(TestIcon),
        show: true,
      })) || []
    return [props.defaultOption, ...sourceOptions]
  }
})

const handleSelected = (key: string) => {
  if (props.onSelected) {
    props.onSelected(props.sources.find((source) => source.id == key) || null)
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
