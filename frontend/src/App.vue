<script setup lang="ts">
import Header from '@/components/Header.vue'
import { onMounted } from 'vue'
import { useSourceStore } from '@/stores/sourceStore'

const sourceStore = useSourceStore()

// 获取图源列表
onMounted(async () => {
  await sourceStore.fetchSources()
})


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
