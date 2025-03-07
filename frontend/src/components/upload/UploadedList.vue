<script setup lang="ts">
import { VueDraggable } from 'vue-draggable-plus'
import { useUploadStateStore } from '@/stores/modules/upload/upload-state.ts'

const uploadStateStore = useUploadStateStore()
</script>

<template>
  <vue-draggable
    v-if="uploadStateStore.data.length"
    v-model="uploadStateStore.data"
    :animation="150"
    target=".n-data-table-tbody"
  >
    <n-data-table
      :columns="uploadStateStore.columns"
      :data="uploadStateStore.data"
      :bordered="false"
    />
  </vue-draggable>
  <n-data-table v-else :columns="uploadStateStore.columns" :bordered="false" />
</template>

<style scoped>
.n-data-table {
  height: 100%;
}
</style>
