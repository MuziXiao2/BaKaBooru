<script setup lang="ts">
import { NSpin } from 'naive-ui'
</script>

<template>
  <n-spin :show="true" description="加载中..." />
</template>

<style scoped>
.n-spin {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 1000;
}
</style>
