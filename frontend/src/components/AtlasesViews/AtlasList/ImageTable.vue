<script setup lang="ts">
import { toRefs } from 'vue'

const props = defineProps({ columns: {}, data: {} })
const { columns, data } = toRefs(props)
</script>

<template>
  <n-data-table :columns="columns" :data="data" :bordered="false" />
</template>

<style scoped></style>
