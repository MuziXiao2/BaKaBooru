<template>
  <el-container class="gallery-container">
    <el-aside class="side" width="250px">
      <slot name="side" />
    </el-aside>
    <el-container class="content-container">
      <el-main class="main">
        <slot name="main" />
      </el-main>
      <el-footer class="footer">
        <slot name="footer" />
      </el-footer>
    </el-container>
  </el-container>
</template>

<script setup lang="ts"></script>

<style scoped>
.gallery-container {
  height: calc(100vh - 60px);
}

.content-container {
  background-color: var(--background-color);
}

.side {
  display: flex;
  flex-direction: column;
  width: 280px;
  background: #ffffff;
  border-right: 1px solid var(--border-color);
  padding: 0;
  overflow-y: auto;
}

.main {
  overflow: hidden;
  padding: 24px;
  height: calc(100vh - 120px);
  background-color: var(--background-color);
}

.footer {
  display: flex;
  height: 60px;
  overflow: hidden;
  align-items: center;
  justify-content: center;
  background-color: #ffffff;
  border-top: 1px solid var(--border-color);
  padding: 0 24px;
}

@media (max-width: 768px) {
  .side {
    width: 240px;
  }

  .main {
    padding: 16px;
  }

  .footer {
    height: 50px;
    padding: 0 16px;
  }
}
</style>
