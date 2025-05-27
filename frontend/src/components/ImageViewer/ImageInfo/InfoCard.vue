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
  background: #f8f8f8;
  margin-bottom: 16px;
}

.title {
  font-size: 18px;
  color: #333;
  margin: 0 0 16px 0;
  font-weight: 600;
}

.content {
  padding: 0 8px;
}
</style>
