<template>
  <el-container>
    <el-aside class="side" width="250px">
      <slot name="side" />
    </el-aside>
    <el-main>
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
  padding: 5px;
  overflow: hidden;
  height: 95vh;
}
</style>
