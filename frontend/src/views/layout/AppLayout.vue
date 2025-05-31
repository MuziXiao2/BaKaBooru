<template>
  <el-container class="container">
    <el-header class="header">
      <slot name="header" />
    </el-header>
    <slot name="default" />
  </el-container>
</template>

<script setup lang="ts"></script>

<style scoped>
.container {
  min-height: 100vh;
  background-color: var(--el-bg-color-page);
}

.header {
  display: flex;
  align-items: center;
  height: 60px;
  padding: 0 24px;
  background-color: var(--el-bg-color);
  border-bottom: 1px solid var(--el-border-color-light);
  box-shadow: var(--el-box-shadow);
  position: relative;
  z-index: 1000;
}

@media (max-width: 768px) {
  .header {
    padding: 0 16px;
    height: 50px;
  }
}
</style>
