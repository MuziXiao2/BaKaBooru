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
        :min-columns="5"
        :max-columns="10"
      />
      <PaginationControl />
    </template>
  </GalleryLayout>
</template>

<script setup lang="ts">
import { onMounted } from 'vue'
import Masonry from '@/components/Masonry.vue'
import Filter from '@/components/Filter.vue'
import GalleryLayout from '@/views/layouts/GalleryLayout.vue'
import AdvancedSearchBar from '@/components/AdvancedSearchBar.vue'
import { useImageSearchStore } from '@/stores/useImageSearchStore'
import PaginationControl from '@/components/PaginationControl.vue'

const store = useImageSearchStore()

onMounted(() => {
  store.goToPage(1) // 设置初始页码
  store.fetchImages() // 触发查询
})
</script>

<style scoped></style>
