<template>
  <teleport to="body">
    <div class="image-viewer-overlay">
      <el-container class="image-viewer-container">
        <!-- 左侧预览组件 -->
        <el-main class="image-viewer-main">
          <slot name="main" />
        </el-main>
        <!-- 右侧信息组件 -->
        <el-aside class="image-viewer-aside">
          <slot name="aside" />
        </el-aside>
      </el-container>
    </div>
  </teleport>
</template>
<script setup lang="ts"></script>

<style scoped>
.image-viewer-overlay {
  position: fixed;
  inset: 0;
  z-index: 1000;
  background-color: var(--el-mask-color);
  overflow: hidden;
  backdrop-filter: blur(8px);
}

.image-viewer-container {
  width: 100%;
  height: 100%;
  display: flex;
  overflow: hidden;
}

.image-viewer-main {
  background: var(--el-bg-color-overlay);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 0;
  height: 100%;
  overflow: hidden;
}

.image-viewer-aside {
  width: 25%;
  min-width: 320px;
  max-width: 480px;
  background: var(--el-bg-color);
  padding: 24px;
  box-shadow: var(--el-box-shadow);
  display: flex;
  flex-direction: column;
  height: 100%;
  overflow: hidden;
  border-left: 1px solid var(--el-border-color-light);
}

@media (max-width: 768px) {
  .image-viewer-aside {
    width: 100%;
    max-width: none;
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    height: 60%;
    border-left: none;
    border-top: 1px solid var(--el-border-color-light);
  }

  .image-viewer-main {
    height: 40%;
  }
}
</style>
