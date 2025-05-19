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
        <div class="image-card">
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

// 图片数据接口
interface ImageItem {
  uuid: string
  title: string
  url: string
}

// Props 定义
defineProps<{
  images: ImageItem[]
  loading: boolean
  columnWidth: number
  gap: number
  ssrColumns: number
  minColumns: number
  maxColumns: number
}>()
</script>

<style scoped>
.wall {
  padding: 1%;
}

/* 图片卡片 */
.image-card {
  width: 100%;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  position: relative; /* 为标题定位提供上下文 */
  overflow: hidden; /* 防止标题溢出 */
  transition: transform 0.3s ease; /* 添加卡片缩放过渡 */
}

.image {
  width: 100%;
  height: auto;
  display: block;
  transition: filter 0.1s ease; /* 图片亮度过渡 */
}

.image-card:hover .image {
  filter: brightness(90%); /* 悬停时图片变暗，突出标题 */
}

/* 标题样式 */
.image-title {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 50%); /* 半透明黑色背景 */
  color: #fff;
  font-size: 14px;
  font-weight: 500;
  text-align: center;
  opacity: 0; /* 默认隐藏 */
  transform: translateY(100%); /* 默认移出底部 */
  transition:
    opacity 0.3s ease,
    transform 0.3s ease; /* 平滑过渡 */
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap; /* 防止标题换行 */
}

/* 悬停时显示标题 */
.image-card:hover .image-title {
  opacity: 1;
  transform: translateY(0); /* 滑入显示 */
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
