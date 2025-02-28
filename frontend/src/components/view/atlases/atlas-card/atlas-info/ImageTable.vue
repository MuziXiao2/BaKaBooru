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
</script>

<template>
  <n-table :single-line="false">
    <thead>
      <tr>
        <th>序号</th>
        <th>标题</th>
        <th>大小</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="image in imageStore.images" :key="image.sn" @click="handleClick(image)">
        <td>{{ image.sn }}</td>
        <td>{{ image.title }}</td>
        <td>0kB</td>
      </tr>
    </tbody>
  </n-table>
</template>

<style scoped></style>
