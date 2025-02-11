<script setup lang="ts">
import Menu from '@/components/Menu.vue'
import SourceList from '@/components/SourceList.vue'
import SearchBar from '@/components/SearchBar.vue'

</script>

<template>
  <n-layout style="height: 100vh;">
    <!--顶栏-->
    <n-layout-header bordered>
      <!--顶部菜单-->
      <Menu />
    </n-layout-header>
    <!--侧边栏-->
    <n-layout has-sider style="height: 100%;">
      <!--图源列表-->
      <SourceList />
      <!--搜索栏-->
      <SearchBar />
      <!--内容-->
      <n-layout-content bordered :native-scrollbar="false">
        <!--路由视图-->
        <router-view />
      </n-layout-content>
    </n-layout>
  </n-layout>

</template>

<style scoped>
.n-layout-header,
.n-layout-footer {
  background: rgba(128, 128, 128, 0.2);
  padding: 24px;
}

.n-layout-sider {
  background: rgba(128, 128, 128, 0.3);
}

.n-layout-content {
  background: rgba(128, 128, 128, 0.4);
}

</style>
