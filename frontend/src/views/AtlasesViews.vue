<script setup lang="ts">
import Sider from '@/components/AtlasesViews/Sider.vue'
import Atlases from '@/components/AtlasesViews/Atlases.vue'

</script>

<template>
  <n-layout has-sider>
    <!--侧边栏-->
    <n-layout-sider
      :show-collapsed-content="true"
      :collapsed-width="10"
      :width="230"
      collapse-mode="transform"
      show-trigger="bar"
      collapsed-trigger-style="margin:10px"
    >
      <Sider />
    </n-layout-sider>

    <!--内容-->
    <n-layout-content >
      <Atlases />
    </n-layout-content>

  </n-layout>
</template>

<style scoped>
.n-layout {
  height: calc(100vh - 43px);

}

.n-layout-sider {
  background: rgba(128, 128, 128, 0.3);
}

.n-layout-content {
  background: rgba(128, 128, 128, 0.4);
  padding: 10px;



}

</style>
