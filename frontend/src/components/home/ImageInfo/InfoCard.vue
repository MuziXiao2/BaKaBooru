<template>
  <section class="info-card">
    <h3 v-if="title" class="title">{{ title }}</h3>
    <div class="content">
      <slot></slot>
    </div>
  </section>
</template>

<script setup lang="ts">
defineProps<{
  title?: string
}>()
</script>

<style scoped>
.info-card {
  padding: 16px;
  border-radius: 8px;
  background: var(--el-bg-color-overlay);
  margin-bottom: 16px;
  border: 1px solid var(--el-border-color-light);
  transition: var(--hover-transition);
}

.info-card:hover {
  border-color: var(--primary-color);
  box-shadow: var(--el-box-shadow);
}

.title {
  font-size: 18px;
  color: var(--el-text-color-primary);
  margin: 0 0 16px 0;
  font-weight: 600;
  display: flex;
  align-items: center;
  gap: 8px;
}

.title::before {
  content: '';
  width: 4px;
  height: 16px;
  background-color: var(--primary-color);
  border-radius: 2px;
}

.content {
  padding: 0 8px;
}
</style>
