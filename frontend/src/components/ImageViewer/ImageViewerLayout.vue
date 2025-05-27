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
  background-color: rgba(0, 0, 0, 0.85);
  overflow: hidden;
}

.image-viewer-container {
  width: 100%;
  height: 100%;
  display: flex;
  overflow: hidden;
}

.image-viewer-main {
  background: #111;
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
  background: #fff;
  padding: 24px;
  box-shadow: -2px 0 8px rgba(0, 0, 0, 0.15);
  display: flex;
  flex-direction: column;
  height: 100%;
  overflow: hidden;
}
</style>
