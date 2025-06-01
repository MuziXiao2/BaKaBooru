<template>
  <GalleryLayout>
    <template #side>
      <AdvancedQuery />
    </template>
    <template #main>
      <ImageMasonry />
    </template>
    <template #footer>
      <PaginationForm />
    </template>
  </GalleryLayout>
  <ImageViewer v-if="currentImageDetail" />
</template>

<script setup lang="ts">
import ImageMasonry from '@/components/home/ImageMasonry.vue'
import AdvancedQuery from '@/components/home/AdvancedQuery.vue'
import GalleryLayout from '@/views/layout/GalleryLayout.vue'
import PaginationForm from '@/components/home/PaginationForm.vue'
import ImageViewer from '@/components/ImageViewer/index.vue'
import { useImageStore } from '@/stores/useImageStore'
import { useImageViewerStore } from '@/stores/useImageViewerStore.ts'
import { onMounted } from 'vue'
import { storeToRefs } from 'pinia'

const imageStore = useImageStore()
const currentImageStore = useImageViewerStore()

const { currentImageDetail } = storeToRefs(currentImageStore)

onMounted(() => {
  imageStore.queryImages()
  imageStore.setPage(1)
})
</script>

<style scoped></style>
