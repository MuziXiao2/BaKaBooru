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
  background-color: var(--background-color);
}

.header {
  display: flex;
  align-items: center;
  height: 60px;
  padding: 0 24px;
  background-color: #ffffff;
  border-bottom: 1px solid var(--border-color);
  box-shadow: 0 2px 4px var(--shadow-color);
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
