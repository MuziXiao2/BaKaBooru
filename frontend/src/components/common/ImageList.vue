<script setup lang="ts">
import type Image from '@/types/module/image'
import { useImageStore } from '@/stores/common/image.ts'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'

const imageStore = useImageStore()
const viewStateStore = useViewStateStore()

const handleClick = async (image: Image) => {
  viewStateStore.setCurrentImage(image)
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

const rowProps = (image: Image) => {
  return {
    style: 'cursor: pointer;',
    onClick: () => handleClick(image),
  }
}
</script>

<template>
  <n-data-table :columns="columns" :data="imageStore.images" :row-props="rowProps" />
</template>

<style scoped></style>
