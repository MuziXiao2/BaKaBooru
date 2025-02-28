<script setup lang="ts">
import { useViewUiStore } from '@/stores/modules/view.ts'
import type Image from '@/stores/types/image'

const viewUiStore = useViewUiStore()

const handleClick = async (image: Image) => {
  viewUiStore.startLoading()
  viewUiStore.setCurrentImage(image)

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
      <tr v-for="image in viewUiStore.images" :key="image.sn" @click="handleClick(image)">
        <td>{{ image.sn }}</td>
        <td>{{ image.title }}</td>
        <td>0kB</td>
      </tr>
    </tbody>
  </n-table>
</template>

<style scoped></style>
