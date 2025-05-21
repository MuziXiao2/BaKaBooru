<template>
  <el-scrollbar>
    <masonry-wall
      class="wall"
      :items="images"
      :column-width="columnWidth"
      :gap="gap"
      :ssr-columns="ssrColumns"
      :min-columns="minColumns"
      :max-columns="maxColumns"
    >
      <template #default="{ item }">
        <div class="image-card" @click="handleClick(item)">
          <img :src="item.url" :alt="item.title" class="image" loading="lazy" />
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

const emit = defineEmits<{
  (e: 'image-click', image: ImageItem): void
}>()

defineProps<{
  images: ImageItem[]
  loading: boolean
  columnWidth: number
  gap: number
  ssrColumns: number
  minColumns: number
  maxColumns: number
}>()

const handleClick = (image: ImageItem) => {
  emit('image-click', image)
}
</script>

<style scoped>
.wall {
  padding: 1%;
}

/* 图片卡片 */
.image-card {
  width: 100%;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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
