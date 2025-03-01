<script setup lang="ts">
import { useViewUiStore } from '@/stores/modules/view/view-ui.ts'

const viewUiStore = useViewUiStore()
</script>

<template>
  <n-layout has-sider>
    <!--左侧边栏-->
    <n-layout-sider
      :collapsed="viewUiStore.isSiderCollapsed"
      :show-collapsed-content="true"
      collapse-mode="transform"
      show-trigger="bar"
      :collapsed-width="0"
      trigger-style=""
      collapsed-trigger-style=""
    >
      <n-card id="sider" size="small" content-style="padding: 5px;">
        <slot name="sider" />
      </n-card>
    </n-layout-sider>
    <n-layout-content :native-scrollbar="false">
      <!--图集列表-->
      <n-card size="small" bordered>
        <slot name="content" />
      </n-card>
      <n-modal
        v-model:show="viewUiStore.showViewAtlas"
        style="height: 95vh; margin: 0 30px; border-radius: 10px"
      >
        <slot name="modal" />
      </n-modal>
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
  padding: 5px;
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

.n-modal {
}
</style>
