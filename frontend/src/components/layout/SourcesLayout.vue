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

<style scoped>
.n-layout {
  height: calc(100vh - 43px);
}

.n-layout-sider {
  background: rgba(128, 128, 128, 0.3);
  padding: 5px 0;
}

.n-layout-content {
  background: rgba(128, 128, 128, 0.4);
  padding: 10px;
}

.n-card {
  height: 100%;
  border: 2px solid gray;
  border-radius: 10px;
}

#sider {
  border: 2px solid gray;
  border-radius: 10px;
}



</style>
