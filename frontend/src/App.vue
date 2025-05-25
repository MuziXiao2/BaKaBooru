<template>
  <AppLayout>
    <template #header>
      <TopForm />
    </template>
    <RouterView />
  </AppLayout>
</template>

<script setup lang="ts">
import AppLayout from '@/components/layout/AppLayout.vue'
import TopForm from '@/components/common/TopForm.vue'
</script>

<style scoped></style>

<style>
html,
body {
  margin: 0;
  padding: 0;
  overflow: hidden;
}
</style>
