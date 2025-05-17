<template>
  <Filter />
  <BottomNav class="bottom-nav" />
</template>

<script lang="ts" setup>
import BottomNav from '@/components/LeftSider/BottomNav.vue'
import Filter from '@/components/LeftSider/Filter.vue'
</script>

<style scoped>
.bottom-nav {
  margin-top: auto;
}
</style>
