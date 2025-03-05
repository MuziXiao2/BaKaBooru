<script setup lang="ts">
import { useViewUiStore } from '@/stores/modules/view/view-ui.ts'

const viewUiStore = useViewUiStore()
</script>

<template>
  <n-layout has-sider :native-scrollbar="false">
    <!--左侧边栏-->
    <n-layout-sider
      :collapsed="viewUiStore.isSiderCollapsed"
      :show-collapsed-content="true"
      collapse-mode="transform"
      :collapsed-width="0"
      trigger-style=""
      collapsed-trigger-style=""
    >
      <slot name="sider" />
    </n-layout-sider>
    <n-layout-content>
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
