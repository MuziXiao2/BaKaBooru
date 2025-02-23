<template>
  <n-spin :show="true" description="加载中...">
    <!-- 可以留空，也可以添加自定义内容 -->
  </n-spin>
</template>

<script setup lang="ts">
import { NSpin } from 'naive-ui';
</script>

<style scoped>
/* 可选：自定义样式 */
.n-spin {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 1000;
}
</style>