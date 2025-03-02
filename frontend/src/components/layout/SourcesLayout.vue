<script setup lang="ts">
import { useSourcesUiStore } from '@/stores/modules/sources/sources-ui.ts'

const sourceUiStore = useSourcesUiStore()
</script>

<template>
  <n-layout has-sider>
    <n-layout-sider
      :collapsed="sourceUiStore.isSiderCollapsed"
      :show-collapsed-content="true"
      collapse-mode="transform"
      :collapsed-width="0"
      trigger-style=""
      collapsed-trigger-style=""
    >
      <slot name="sider" />
    </n-layout-sider>
    <n-layout-content :native-scrollbar="false">
      <slot name="content" />
    </n-layout-content>
  </n-layout>
</template>

<style scoped></style>
