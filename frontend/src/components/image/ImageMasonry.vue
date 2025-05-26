<template>
  <el-scrollbar>
    <masonry-wall
      class="wall"
      :items="images"
      :column-width="300"
      :gap="10"
      :ssr-columns="1"
      :min-columns="minColumns"
      :max-columns="maxColumns"
    >
      <template #default="{ item }">
        <div class="image-card" @click="handleImageClick(item)">
          <el-image
            :src="item.url"
            :alt="item.title"
            class="image"
            fit="cover"
            lazy
          />
          <div class="image-title">{{ item.title }}</div>
        </div>
      </template>
    </masonry-wall>
    <div v-if="loading" class="loading">加载中...</div>
    <div v-if="!loading && images.length === 0" class="no-data">暂无图片</div>
  </el-scrollbar>
</template>

<script setup lang="ts">
import MasonryWall from '@yeger/vue-masonry-wall'
import { ElScrollbar } from 'element-plus'
import type { ImageItem } from '@/types'
import { useImageStore } from '@/stores/useImageStore'
import { storeToRefs } from 'pinia'
import { ref, onMounted, onUnmounted } from 'vue'
import { useCurrentImageStore } from '@/stores/useCurrentImageStore.ts'

const imageStore = useImageStore()
const currentImageStore = useCurrentImageStore()
const { loading, images } = storeToRefs(imageStore)

const handleImageClick = async (image: ImageItem) => {
  await currentImageStore.setCurrentImage(image)
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
})

onUnmounted(() => {
  window.removeEventListener('resize', updateColumns)
})
</script>

<style scoped>
.wall {
  padding: 1%;
}

/* 图片卡片 */
.image-card {
  width: 100%;
  border: #b7b7b7 solid 1px;
  border-radius: 5px;
  position: relative;
  overflow: hidden;
  transition: transform 0.3s ease;
  cursor: pointer;
}

.image {
  width: 100%;
  height: auto;
  display: block;
  transition: filter 0.1s ease;
}

.image-card:hover .image {
  filter: brightness(90%);
}

.image-title {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 50%);
  color: #fff;
  font-size: 14px;
  font-weight: 500;
  text-align: center;
  opacity: 0;
  transform: translateY(100%);
  transition:
    opacity 0.3s ease,
    transform 0.3s ease;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.image-card:hover .image-title {
  opacity: 1;
  transform: translateY(0);
}

.loading,
.no-data {
  text-align: center;
  padding: 20px;
  font-size: 16px;
  color: #666;
}
</style>
