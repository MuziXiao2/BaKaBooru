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
  display: flex;
}

.content-container {
  flex: 1;
  display: flex;
  flex-direction: column;
  background-color: var(--el-bg-color-page);
  min-width: 0; /* 防止flex子项溢出 */
}

.side {
  display: flex;
  flex-direction: column;
  width: 280px;
  color: var(--el-text-color-primary);
  border-right: 1px solid var(--el-border-color-light);
  background-color: var(--el-bg-color);
  padding: 0;
  overflow-y: auto;
  flex-shrink: 0; /* 防止侧边栏被压缩 */
}

.main {
  flex: 1;
  overflow: hidden;
  padding: 0;
  display: flex;
  min-height: 0; /* 允许内容区域正确滚动 */
}

.footer {
  display: flex;
  height: 60px;
  overflow: hidden;
  align-items: center;
  justify-content: center;
  background-color: var(--el-bg-color);
  border-top: 1px solid var(--el-border-color-light);
  padding: 0 24px;
  flex-shrink: 0; /* 防止footer被压缩 */
}

@media (max-width: 768px) {
  .side {
    width: 240px;
  }

  .footer {
    height: 50px;
    padding: 0 16px;
  }
}
</style>
