<template>
  <el-container class="container">
    <el-header class="header">
      <TopBar />
    </el-header>
    <el-container>
      <el-aside class="aside" v-if="globalUiStore.isLeftMenutoggle" width="250px">
        <LeftSider />
      </el-aside>
      <el-main class="main">
        <RouterView />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import TopBar from '@/components/TopBar.vue'
import LeftSider from '@/components/LeftSider.vue'
import { useGlobalUiStore } from '@/stores/index.ts'

const globalUiStore = useGlobalUiStore()
</script>

<style scoped>
.container {
  height: 100vh;
}

.header {
  display: flex;
  border-bottom: 2px solid #ccc;
  overflow: hidden;
  align-items: center;
  height: 60px;
  background-color: #f5f5f5;
}

.aside {
  display: flex;
  overflow: hidden;
  background: #f2f2f2;
  flex-direction: column;
  height: 100%;
}

.resizer {
  width: 3px;
  cursor: ew-resize;
  background-color: #ccc;
}

.main {
  display: flex;
  overflow: hidden;
}
</style>
