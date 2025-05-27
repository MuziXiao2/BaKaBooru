<template>
  <ImageViewerLayout>
    <template #main>
      <ImagePreview />
    </template>
    <template #aside>
      <Index />
    </template>
  </ImageViewerLayout>
</template>

<script setup lang="ts">
import ImageViewerLayout from './ImageViewerLayout.vue'
import ImagePreview from './ImagePreview.vue'
import Index from './ImageInfo/index.vue'
</script>

<style scoped></style>
