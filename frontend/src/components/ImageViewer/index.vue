<template>
  <ImageViewerLayout>
    <template #main>
      <ImagePreview />
    </template>
    <template #aside>
      <ImageInfo />
    </template>
  </ImageViewerLayout>
</template>

<script setup lang="ts">
import ImageViewerLayout from './ImageViewerLayout.vue'
import ImagePreview from './ImagePreview.vue'
import ImageInfo from './ImageInfo/index.vue'
</script>

<style scoped></style>
