<script setup lang="ts">
import { ref } from 'vue'
import Draggable from 'vuedraggable'
import { NDataTable } from 'naive-ui'

const list = ref([
  { id: 'img1', type: 'Image', name: 'Image 1' },
  { id: 'atlas1', type: 'Atlas', images: [{ id: 'img2', type: 'Image', name: 'Image 2' }] },
])

const columns = [
  { title: 'Type', key: 'type' },
  { title: 'Name', key: 'name' },
]
</script>

<template>
  <Draggable v-model="list" item-key="id" ghost-class="ghost">
    <template #item="{ element }">
      <n-data-table :columns="columns" :data="[element]" :row-key="(row) => row.id" />
    </template>
  </Draggable>
</template>

<style>
.ghost {
  border: 2px dashed #1890ff;
  opacity: 0.5;
}
</style>
