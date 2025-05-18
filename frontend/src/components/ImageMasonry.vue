<template>
  <div class="container">
    <el-scrollbar class="scroll-container" :height="height">
      <masonry-wall
        :items="images"
        :column-width="columnWidth"
        :gap="gap"
        :ssr-columns="ssrColumns"
        :min-columns="minColumns"
        :max-columns="maxColumns"
      >
        <template #default="{ item }">
          <div class="image-card">
            <img :src="item.url" :alt="item.alt" class="image" loading="lazy" />
          </div>
        </template>
      </masonry-wall>
      <div v-if="loading" class="loading">加载中...</div>
      <div v-if="!loading && images.length === 0" class="no-data">暂无图片</div>
    </el-scrollbar>
  </div>
</template>

<script setup lang="ts">
import MasonryWall from '@yeger/vue-masonry-wall'
import { ElScrollbar } from 'element-plus'

// 图片数据接口
interface ImageItem {
  id: string
  url: string
  alt: string
}

// Props 定义
defineProps<{
  images: ImageItem[]
  loading: boolean
  height: string
  columnWidth: number
  gap: number
  ssrColumns: number
  minColumns: number
  maxColumns: number
}>()
</script>

<style scoped>
/* 容器样式 */
.container {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

/* 滚动容器 */
.scroll-container {
  width: 100%;
}

/* 图片卡片 */
.image-card {
  width: 100%;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.image {
  width: 100%;
  height: auto;
  display: block;
}

/* 状态提示 */
.loading,
.no-data {
  text-align: center;
  padding: 20px;
  font-size: 16px;
  color: #666;
}
</style>
