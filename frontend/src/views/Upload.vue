<script setup lang="ts">
import UploadLayout from '@/components/layout/UploadLayout.vue'
import UploadedList from '@/components/upload/UploadedList.vue'
import Uploader from '@/components/upload/Uploader.vue'
</script>

<template>
  <upload-layout>
    <template #header>
      <Uploader />
    </template>
    <template #content>
      <UploadedList />
    </template>
    <template #sider>
      <n-h1>Atlas Info</n-h1>
    </template>
  </upload-layout>
</template>

<style scoped>

</style>
