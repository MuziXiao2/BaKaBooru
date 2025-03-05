<script setup lang="ts">

import { VueDraggable } from 'vue-draggable-plus'
import { h, ref } from 'vue'
import { NInput } from 'naive-ui'

function createData() {
  const data = []
  for (let sn = 0; sn < 10; sn++) {
    data.push({
      sn: sn,
      title: 'Title ' + sn,
      images: Math.floor(Math.random() * 10),
    })
  }

  return data
}

const data = ref(createData())

const columns = [
  {
    type: 'selection',
  },
  {
    title: '图集标题',
    key: 'title',
    render(row, index) {
      return h(NInput, {
        value: row.name,
        onUpdateValue(v) {
          data.value[index].name = v
        },
      })
    },
  },
  {
    title: '包含图片',
    key: 'images',
  },
]
</script>

<template>
  <VueDraggable v-model="data" :animation="150" target=".n-data-table-tbody">
    <n-data-table :columns="columns" :data="data" :bordered="false" />
  </VueDraggable>
</template>

<style scoped>

</style>
