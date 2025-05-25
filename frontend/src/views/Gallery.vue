<template>
  <GalleryLayout>
    <template #side>
      <TagFilter />
    </template>
    <template #header> </template>
    <template #main>
      <ImageMasonry />
      <PaginationForm />
    </template>
  </GalleryLayout>
  <ImageViewer v-if="selectedImageStore.currentImageDetail" />
</template>

<script setup lang="ts">
import ImageMasonry from '@/components/image/ImageMasonry.vue'
import TagFilter from '@/components/common/TagFilter.vue'
import GalleryLayout from '@/components/layout/GalleryLayout.vue'
import AdvancedQueryForm from '@/components/gallery/AdvancedQueryForm.vue'
import PaginationForm from '@/components/gallery/PaginationForm.vue'
import ImageViewer from '@/components/image/ImageViewer.vue'
import { useImageStore } from '@/stores/useImageStore'
import { useCurrentImageStore } from '@/stores/useCurrentImageStore.ts'
import { usePaginationStore } from '@/stores/usePaginationStore'
import { onMounted } from 'vue'

const imageStore = useImageStore()
const paginationStore = usePaginationStore()
const selectedImageStore = useCurrentImageStore()

onMounted(() => {
  paginationStore.goToPage(1)
  imageStore.queryImages()
})
</script>

<style scoped></style>
