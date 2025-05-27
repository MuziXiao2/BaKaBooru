<template>
  <InfoCard title="文件列表">
    <div class="thumbnail-list">
      <el-row :gutter="8">
        <el-col v-for="(url, index) in urls" :key="url" :span="4" class="thumbnail-item">
          <el-image
            :src="url"
            :class="['thumbnail', { selected: index === currentIndex }]"
            fit="cover"
            @click="handleClick(index)"
          />
        </el-col>
      </el-row>
    </div>
  </InfoCard>
</template>

<script setup lang="ts">
import InfoCard from './InfoCard.vue'
import { useImageViewerStore } from '@/stores/useImageViewerStore.ts'
import { storeToRefs } from 'pinia'

const imageViewerStore = useImageViewerStore()
const { currentThumbnailFileUrls: urls, currentFileIndex: currentIndex } =
  storeToRefs(imageViewerStore)

function handleClick(index: number) {
  if (index === currentIndex.value) return
  imageViewerStore.setCurrentFileIndex(index)
}
</script>

<style scoped>
.thumbnail-list {
  width: 100%;
  padding: 10px;
  box-sizing: border-box;
  overflow-x: auto;
  overflow-y: hidden;
}

.thumbnail-list :deep(.el-row) {
  display: flex;
  flex-wrap: nowrap;
  width: 100%;
}

.thumbnail-item {
  flex: 0 0 auto;
  width: min(80px, calc(100% / 6));
  height: 80px;
  cursor: pointer;
  margin-bottom: 8px;
}

.thumbnail {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border: 2px solid transparent;
  border-radius: 4px;
  transition: all 0.3s ease;
}

.thumbnail:hover {
  border-color: #66b1ff;
}

.thumbnail.selected {
  border-color: #409eff;
}
</style>
