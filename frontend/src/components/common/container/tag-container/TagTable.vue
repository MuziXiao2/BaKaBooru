<script setup lang="ts">
import Tag from '@/types'
import { computed } from 'vue'

const props = defineProps<{
  tags: Tag[]
  isLoaded: boolean
  onClick?: (tag: Tag) => void
}>()

const columns = [
  {
    title: 'Name',
    key: 'name',
  },
  {
    title: 'Count',
    key: 'count',
  },
  {
    title: 'Description',
    key: 'description',
  },
]

function rowProps(row) {
  return {
    style: 'cursor: pointer;',
    onClick: () => {
      props.onClick()
    },
  }
}

const data = computed(() =>
  props.tags.map((tag, index) => ({
    key: index,
    name: tag.name,
    count: tag.count,
    description: tag.description,
  })),
)
</script>

<template>
  <n-data-table :bordered="false" :columns="columns" :data="data" :row-props="rowProps" />
</template>

<style scoped></style>
