<template>
  <el-container style="height: 100vh">
    <el-header height="60px">
      <TopBar />
    </el-header>
    <el-container>
      <el-aside width="200px">
        <SideMenu />
      </el-aside>
      <el-main>
        <RouterView />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import TopBar from '@/components/TopBar.vue'
import SideMenu from '@/components/SideMenu.vue'
</script>
