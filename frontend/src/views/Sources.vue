<script setup lang="ts">
import SourcesLayout from '@/components/layout/SourcesLayout.vue'
import Sources from '@/components/sources/Sources.vue'
import SourceGroupList from '@/components/sources/SourceGroupList.vue'
</script>

<template>
  <sources-layout>
    <template #sider>
      <source-group-list />
    </template>
    <template #content>
      <sources />
    </template>
  </sources-layout>
</template>

<style scoped></style>
