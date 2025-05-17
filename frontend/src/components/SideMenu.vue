<template>
  <el-menu router default-active="/home" class="el-menu-vertical">
    <el-menu-item index="/home">
      <el-icon><House /></el-icon>
      首页
    </el-menu-item>
    <el-menu-item index="/upload">
      <el-icon><Upload /></el-icon>
      上传
    </el-menu-item>
    <el-menu-item index="/atlas">
      <el-icon><Collection /></el-icon>
      图集
    </el-menu-item>
  </el-menu>
</template>

<script setup>
import { House, Upload, Collection } from '@element-plus/icons-vue'
</script>

<style scoped>
.el-menu-vertical {
  height: 100%;
}
</style>
