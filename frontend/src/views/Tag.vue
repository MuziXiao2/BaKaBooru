<script setup lang="ts">
import TagLayout from '@/views/layout/TagLayout.vue'

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

const data = [
  {
    key: 0,
    name: 'a',
    count: 1,
    description: '666',
  },
  {
    key: 1,
    name: 'b',
    count: 1,
    description: '666',
  },
  {
    key: 2,
    name: 'c',
    count: 1,
    description: '666',
  },
]



</script>

<template>
  <tag-layout>
    <template #filter> 666 </template>
    <template #list>
    </template>
  </tag-layout>
</template>

<style scoped></style>
