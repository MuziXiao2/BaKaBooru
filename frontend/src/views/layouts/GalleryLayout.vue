<template>
  <el-container>
    <el-aside class="side" width="250px">
      <slot name="side" />
    </el-aside>
    <el-main style="padding: 0">
      <el-container>
        <el-header class="header">
          <slot name="header" />
        </el-header>
        <el-main class="main">
          <slot name="main" />
        </el-main>
      </el-container>
    </el-main>
  </el-container>
</template>

<script setup lang="ts"></script>

<style scoped>
.side {
  display: flex;
  border-right: 2px solid #ccc;
  background: #f2f2f2;
  flex-direction: column;
}

.main {
  overflow: hidden;
  padding: 0;
  height: 91vh;
}

.header {
  background: #f2f2f2;
  border-bottom: 2px solid #ccc;
  display: flex;
  align-items: center;
  height: 4vh;
}
</style>
