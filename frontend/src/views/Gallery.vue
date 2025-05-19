<template>
  <GalleryLayout>
    <template #side>
      <Filter />
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
      />
      <PaginationControl />
    </template>
  </GalleryLayout>
</template>

<script setup lang="ts">
import Masonry from '@/components/Masonry.vue'
import Filter from '@/components/Filter.vue'
import GalleryLayout from '@/views/layouts/GalleryLayout.vue'
import AdvancedSearchBar from '@/components/AdvancedSearchBar.vue'
import { useImageSearchStore } from '@/stores/useImageSearchStore'
import PaginationControl from '@/components/PaginationControl.vue'
import { ref, onMounted, onUnmounted } from 'vue'

const minColumns = ref(5)
const maxColumns = ref(10)

// 监听窗口大小变化，动态设置列数
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
const store = useImageSearchStore()

onMounted(() => {
  updateColumns()
  window.addEventListener('resize', updateColumns)
  store.goToPage(1) // 设置初始页码
  store.fetchImages() // 触发查询
})

onUnmounted(() => {
  window.removeEventListener('resize', updateColumns)
})
</script>

<style scoped></style>
