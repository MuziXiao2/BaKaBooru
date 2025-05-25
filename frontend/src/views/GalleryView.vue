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
import ImageMasonry from '@/components/image/ImageMasonry.vue'
import AdvancedQuery from '@/components/common/AdvancedQuery.vue'
import GalleryLayout from '@/components/layout/GalleryLayout.vue'
import PaginationForm from '@/components/common/PaginationForm.vue'
import ImageViewer from '@/components/image/ImageViewer.vue'
import { useImageStore } from '@/stores/useImageStore'
import { useCurrentImageStore } from '@/stores/useCurrentImageStore.ts'
import { onMounted } from 'vue'
import { storeToRefs } from 'pinia'

const imageStore = useImageStore()
const currentImageStore = useCurrentImageStore()

const { currentImageDetail } = storeToRefs(currentImageStore)

onMounted(() => {
  imageStore.queryImages()
  imageStore.setPage(1)
})
</script>

<style scoped></style>
