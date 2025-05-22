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
        :min-columns="minColumns"
        :max-columns="maxColumns"
        @image-click="handleImageClick"
      />
      <PaginationControl />
    </template>
  </GalleryLayout>

  <ImageViewer v-if="selectedImageStore.selectedImageDetails" />
</template>

<script setup lang="ts">
import Masonry from '@/components/Masonry.vue'
import TagFilter from '@/components/TagFilter.vue'
import GalleryLayout from '@/views/layouts/GalleryLayout.vue'
import AdvancedSearchBar from '@/components/AdvancedSearchBar.vue'
import PaginationControl from '@/components/PaginationControl.vue'
import ImageViewer from '@/components/ImageViewer.vue'
import { useImageStore } from '@/stores/useImageStore'
import { useSelectedImageStore } from '@/stores/useSelectedImageStore'
import { usePaginationStore } from '@/stores/usePaginationStore'
import { ref, onMounted, onUnmounted } from 'vue'
import type { ImageItem } from '@/types'

const imageStore = useImageStore()
const selectedImageStore = useSelectedImageStore()
const paginationStore = usePaginationStore()

const handleImageClick = async (image: ImageItem) => {
  await selectedImageStore.setSelectedImage(image)
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
  paginationStore.goToPage(1)
  imageStore.fetchImages()
})

onUnmounted(() => {
  window.removeEventListener('resize', updateColumns)
})
</script>

<style scoped></style>
