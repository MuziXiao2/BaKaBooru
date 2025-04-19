<script setup lang="ts">
import type { Group, Source } from '@/types'
import { computed } from 'vue'

const props = defineProps<{
  groups: Group[]
  sources: Source[]
  onSelected?: (source: Source) => void
}>()

const selectOptions = computed(() =>
  props.groups.map((group) => ({
    type: 'group',
    label: group.name,
    key: group.sn,
    children:
      props.sources
        .filter((source) => source.groupId === group.id)
        ?.map((source) => ({
          label: source.name,
          value: source.id,
        })) || [],
  })),
)

const handleSelected = (value: number) => {
  if (props.onSelected) {
    props.onSelected(props.sources.find((source) => source.id == value) || null)
  }
}
</script>

<template>
  <n-select
    @update:value="handleSelected"
    placeholder="请选择图源"
    :options="selectOptions"
    class="short-select"
  />
</template>

<style scoped></style>
