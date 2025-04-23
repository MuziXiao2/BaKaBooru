<script setup lang="ts">
import TagLayout from '@/views/layout/TagLayout.vue'
import TagTable from '@/components/common/container/tag-container/TagTable.vue'
import { useTagStore } from '@/stores/common/tag.ts'
import { onMounted } from 'vue'

const tagStore = useTagStore()

onMounted(() => tagStore.update())
</script>

<template>
  <tag-layout>
    <template #filter> 666</template>
    <template #list>
      <tag-table :tags="tagStore.tags" :is-loaded="tagStore.isTagsLoaded" />
    </template>
  </tag-layout>
</template>

<style scoped></style>
