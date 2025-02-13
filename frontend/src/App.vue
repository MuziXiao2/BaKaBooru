<script setup lang="ts">
import Header from '@/components/Header.vue'

</script>

<template>
  <n-layout style="height: 100vh;">
    <!--头部-->
    <Header />
    <!--主体-->
    <router-view />
  </n-layout>
</template>

<style scoped>


</style>
