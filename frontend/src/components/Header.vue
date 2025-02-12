<script setup lang="ts">

import TopMenu from '@/components/Header/TopMenu.vue'
</script>

<template>
  <n-layout-header bordered>
    <TopMenu />
  </n-layout-header>
</template>

<style scoped>
.n-layout-header {
  background: rgba(128, 128, 128, 0.2);
}

</style>
