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

<style scoped></style>

<style>
html,
body {
  margin: 0;
  padding: 0;
  overflow: hidden;
}

</style>
