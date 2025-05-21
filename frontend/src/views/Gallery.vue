<!-- Gallery.vue -->
<template>
  <GalleryLayout>
    <template #side>
      <TagFilter />
    </template>
    <template #header>
      <AdvancedSearchBar />
    </template>
    <template #main>
      <Masonry
        :images="store.images"
        :loading="store.loading"
        :column-width="300"
        :gap="10"
        :ssr-columns="1"
        :min-columns="minColumns"
        :max-columns="maxColumns"
        @image-click="handleImageClick"
      />
      <PaginationControl />
    </template>
  </GalleryLayout>

  <ImageViewer
    v-if="store.selectedImage"
    :image="store.selectedImage"
    :image-details="store.selectedImageDetails"
    @close="() => store.setSelectedImage(null)"
    @update="handleImageUpdate"
  />
</template>

<script setup lang="ts">
import Masonry from '@/components/Masonry.vue'
import TagFilter from '@/components/TagFilter.vue'
import GalleryLayout from '@/views/layouts/GalleryLayout.vue'
import AdvancedSearchBar from '@/components/AdvancedSearchBar.vue'
import { useImageStore } from '@/stores/useImageStore.ts'
import PaginationControl from '@/components/PaginationControl.vue'
import ImageViewer from '@/components/ImageViewer.vue'
import { ref, onMounted, onUnmounted } from 'vue'
import type { ImageItem } from '@/types'

const store = useImageStore()

const handleImageClick = async (image: ImageItem) => {
  await store.setSelectedImage(image)
}

const handleImageUpdate = (updated: { uuid: string; title: string }) => {
  store.updateImageTitle(updated.uuid, updated.title)
}

const minColumns = ref(5)
const maxColumns = ref(10)

const updateColumns = () => {
  const width = window.innerWidth
  if (width >= 1600) {
    minColumns.value = 6
    maxColumns.value = 10
  } else if (width >= 1200) {
    minColumns.value = 4
    maxColumns.value = 8
  } else if (width >= 768) {
    minColumns.value = 3
    maxColumns.value = 6
  } else {
    minColumns.value = 1
    maxColumns.value = 3
  }
}

onMounted(() => {
  updateColumns()
  window.addEventListener('resize', updateColumns)
  store.goToPage(1)
  store.fetchImages()
})

onUnmounted(() => {
  window.removeEventListener('resize', updateColumns)
})
</script>

<style scoped></style>
