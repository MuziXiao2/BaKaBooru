<template>
  <el-container class="container">
    <el-header class="header">
      <slot name="header" />
    </el-header>
    <slot name="default" />
  </el-container>
</template>

<script setup></script>

<style scoped>
.header {
  display: flex;
  border-bottom: 2px solid #ccc;
  align-items: center;
  height: 5vh;
  background-color: #f5f5f5;
}
</style>
