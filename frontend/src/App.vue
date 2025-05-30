<template>
  <AppLayout>
    <template #header>
      <TopBar />
    </template>
    <RouterView />
  </AppLayout>
</template>

<script setup lang="ts">
import AppLayout from '@/views/layout/AppLayout.vue'
import TopBar from '@/components/common/TopBar.vue'
</script>

<style>
:root {
  --primary-color: #3a7bd5;
  --secondary-color: #00d2ff;
  --background-color: #f8f9fa;
  --text-color: #2c3e50;
  --border-color: #e9ecef;
  --shadow-color: rgba(0, 0, 0, 0.1);
  --transition-speed: 0.3s;
}

html,
body {
  margin: 0;
  padding: 0;
  overflow: hidden;
  font-family:
    -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans',
    'Helvetica Neue', sans-serif;
  color: var(--text-color);
  background-color: var(--background-color);
}

* {
  box-sizing: border-box;
  transition: all var(--transition-speed) ease;
}

.el-button {
  border-radius: 6px;
  font-weight: 500;
}

.el-input {
  border-radius: 6px;
}

.el-card {
  border-radius: 8px;
  box-shadow: 0 4px 6px var(--shadow-color);
}
</style>
