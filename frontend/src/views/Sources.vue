<script setup lang="ts">
import SourcesLayout from '@/components/layout/SourcesLayout.vue'
import SourceList from '@/components/sources/SourceList.vue'
import SourceGroupList from '@/components/sources/SourceGroupList.vue'
</script>

<template>
  <sources-layout>
    <template #sider>
      <source-group-list />
    </template>
    <template #content>
      <source-list />
    </template>
  </sources-layout>
</template>

<style scoped></style>
