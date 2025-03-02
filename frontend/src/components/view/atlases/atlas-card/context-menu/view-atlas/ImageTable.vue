<script setup lang="ts">
import type Image from '@/types/image'
import { useImageStore } from '@/stores/common/image.ts'
import { useViewUiStore } from '@/stores'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'

const imageStore = useImageStore()
const viewUiStore = useViewUiStore()
const viewStateStore = useViewStateStore()

const handleClick = async (image: Image) => {
  viewUiStore.startLoading()
  viewStateStore.setCurrentImage(image)
  viewUiStore.stopLoading()
}

const columns = [
  {
    title: '序号',
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
