<template>
  <el-button
    class="toggle"
    text
    :icon="globalUiStore.isLeftMenutoggle ? Fold : Expand"
    @click="globalUiStore.toggleLeftMenu"
  />
  <el-breadcrumb :separator-icon="ArrowRight">
    <el-breadcrumb-item :to="{ path: '/' }">BaKaBooru</el-breadcrumb-item>
    <el-breadcrumb-item>{{ globalUiStore.activePage }}</el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup>
import { Fold, Expand } from '@element-plus/icons-vue'
import { ArrowRight } from '@element-plus/icons-vue'
import { useGlobalUiStore } from '@/stores/index.js'

const globalUiStore = useGlobalUiStore()
</script>

<style scoped>
.top-bar {
  justify-content: space-between;
  align-items: center;
  height: 100%;
}

.title {
  font-size: 18px;
  font-weight: bold;
}

.toggle {
  font-size: 24px;
  padding: 12px;
}
</style>
