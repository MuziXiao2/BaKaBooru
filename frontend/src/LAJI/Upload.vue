<script setup lang="ts">
import UploadLayout from '@/layouts/UploadLayout.vue'
import UploadedList from '@/LAJI/UploadedList.vue'
import Uploader from '@/LAJI/Uploader.vue'
</script>

<template>
  <upload-layout>
    <template #header>
      <uploader />
    </template>
    <template #content>
      <uploaded-list />
    </template>
    <template #sider>
      <n-h1>Atlas Info</n-h1>
    </template>
  </upload-layout>
</template>

<style scoped>

</style>
