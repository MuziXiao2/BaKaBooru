<template>
  <GalleryLayout>
    <template #side>
      <TagFilter />
    </template>
    <template #header>
      <AdvancedSearchBar />
    </template>
    <template #main>
      <ImageMasonry />
      <PaginationControl />
    </template>
  </GalleryLayout>
  <ImageViewer v-if="selectedImageStore.currentImageDetail" />
</template>

<script setup lang="ts">
import ImageMasonry from '@/components/ImageMasonry.vue'
import TagFilter from '@/components/TagFilter.vue'
import GalleryLayout from '@/layouts/GalleryLayout.vue'
import AdvancedSearchBar from '@/components/AdvancedSearchBar.vue'
import PaginationControl from '@/components/PaginationControl.vue'
import ImageViewer from '@/components/ImageViewer.vue'
import { useImageStore } from '@/stores/useImageStore'
import { useCurrentImageStore } from '@/stores/useSelectedImageStore'
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
