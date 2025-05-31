<template>
  <div class="masonry-container">
    <el-scrollbar class="scrollbar-container">
      <div class="wall-wrapper">
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
              <el-image :src="item.url" :alt="item.title" class="image" fit="cover" lazy />
              <div class="image-title">{{ item.title }}</div>
            </div>
          </template>
        </masonry-wall>
        <div v-if="loading" class="loading">加载中...</div>
        <div v-if="!loading && images.length === 0" class="no-data">暂无图片</div>
      </div>
    </el-scrollbar>
  </div>
</template>

<script setup lang="ts">
import MasonryWall from '@yeger/vue-masonry-wall'
import { ElScrollbar } from 'element-plus'
import type { ImageItem } from '@/types'
import { useImageStore } from '@/stores/useImageStore.ts'
import { storeToRefs } from 'pinia'
import { ref, onMounted, onUnmounted } from 'vue'
import { useImageViewerStore } from '@/stores/useImageViewerStore.ts'

const imageStore = useImageStore()
const currentImageStore = useImageViewerStore()
const { loading, images } = storeToRefs(imageStore)

const handleImageClick = async (image: ImageItem) => {
  await currentImageStore.openImageViewer(image.uuid)
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
.masonry-container {
  height: 100%;
  width: 100%;
  position: relative;
}

.scrollbar-container {
  height: 100%;
}

.wall-wrapper {
  min-height: 100vh;
  height: 100%;
  padding: 16px;
  background-color: var(--el-bg-color);
}

.wall {
  height: 100%;
}

:deep(.masonry-wall) {
  height: 100% !important;
  min-height: inherit !important;
}

/* 图片卡片 */
.image-card {
  width: 100%;
  border: 1px solid var(--el-border-color-light);
  border-radius: 8px;
  position: relative;
  overflow: hidden;
  transition: var(--hover-transition);
  cursor: pointer;
  background-color: var(--el-bg-color-overlay);
  box-shadow: var(--el-box-shadow);
}

.image-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  border-color: var(--primary-color);
}

.image {
  width: 100%;
  height: auto;
  display: block;
  transition: all 0.3s ease;
}

.image-card:hover .image {
  filter: brightness(1.1);
}

.image-title {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  background: var(--el-mask-color);
  color: var(--el-text-color-primary);
  font-size: 14px;
  font-weight: 500;
  text-align: center;
  padding: 8px;
  opacity: 0;
  transform: translateY(100%);
  transition: all 0.3s ease;
  backdrop-filter: blur(4px);
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
  color: var(--el-text-color-secondary);
  background-color: var(--el-bg-color-overlay);
  border-radius: 8px;
  margin: 20px 0;
}

:deep(.el-scrollbar__wrap) {
  overflow-x: hidden;
}
</style>
