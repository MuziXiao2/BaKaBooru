<script setup lang="ts">
import Sider from '@/components/AtlasesViews/Sider.vue'

</script>

<template>
  <n-layout has-sider>
    <!--左侧边栏-->
    <n-layout-sider
      :show-collapsed-content="true"
      collapse-mode="transform"
      show-trigger="bar"
      :collapsed-width="0"
      trigger-style=""
      collapsed-trigger-style=""
    >
      <Sider />
    </n-layout-sider>

    <!--内容-->
    <n-layout-content  :native-scrollbar="false">
      <n-card
        size="small"
        bordered
      >
        <router-view />
      </n-card>
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

</style>
