<script setup lang="ts">
import { useUiStore } from '@/stores'

const uiStore = useUiStore()
</script>

<template>
  <n-layout>
    <n-layout-header style="height: 3%; padding: 6px">
      <slot name="header" />
    </n-layout-header>
    <n-layout has-sider style="height: 97%">
      <!--左侧边栏-->
      <n-layout-sider
        :collapsed="uiStore.isSiderCollapsed"
        :show-collapsed-content="true"
        collapse-mode="transform"
        :collapsed-width="0"
        trigger-style=""
        collapsed-trigger-style=""
      >
        <slot name="left-sider" />
      </n-layout-sider>
      <n-layout-content>
        <slot name="content" />
      </n-layout-content>
    </n-layout>
  </n-layout>
</template>

<style scoped>
.n-layout-sider {
  background: rgba(128, 128, 128, 40%);
  padding: 1px 0;
}

.n-layout-content {
  background: rgba(128, 128, 128, 50%);
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
