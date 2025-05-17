<script setup lang="ts">
import type { Image } from '@/types'
import LoadingSpin from '@/LAJI/content/LoadingSpin.vue'
import { computed } from 'vue'

const props = defineProps<{
  images: Image[]
  isLoaded: boolean
  onClick?: (image: Image) => void
}>()

const handleClick = (row) => {
  if (props.onClick) {
    props.onClick(props.images[row.sn])
  }
}

const columns = [
  {
    title: '#',
    key: 'sn',
  },
  {
    title: '标题',
    key: 'title',
  },
  {
    title: '大小',
    key: 'size',
  },
]

const rowProps = (row) => ({
  style: 'cursor: pointer;',
  onClick: () => handleClick(row),
})

const data = computed(() =>
  props.images.map((image, index) => ({
    sn: index,
    title: image.title,
    size: image.size,
  })),
)
</script>

<template>
  <n-data-table v-if="isLoaded" :columns="columns" :data="data" :row-props="rowProps" />
  <loading-spin v-else />
</template>

<style scoped></style>
