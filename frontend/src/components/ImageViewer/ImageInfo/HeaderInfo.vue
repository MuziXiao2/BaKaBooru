<template>
  <InfoCard title="图片详情">
    <h2 class="title">{{ image.title || '未命名图片' }}</h2>
    <div class="meta">
      <div class="meta-item">
        <el-icon>
          <User />
        </el-icon>
        <span>{{ image.creator || '未知' }}</span>
      </div>
      <div class="meta-dates">
        <div class="meta-item">
          <el-icon>
            <Clock />
          </el-icon>
          <span>创建于 {{ image.createdAt || '未知' }}</span>
        </div>
        <el-icon>
          <Clock />
        </el-icon>
        <span>更新于 {{ image.updatedAt || '未知' }}</span>
      </div>
    </div>
  </InfoCard>
</template>

<script setup lang="ts">
import { Clock, User } from '@element-plus/icons-vue'
import InfoCard from './InfoCard.vue'
import { useImageViewerStore } from '@/stores/useImageViewerStore.ts'
import { storeToRefs } from 'pinia'

const imageViewerStore = useImageViewerStore()
const { currentImageDetail: image } = storeToRefs(imageViewerStore)
</script>

<style scoped>
.title {
  font-size: 24px;
  font-weight: 600;
  color: #333;
  margin: 0 0 16px 0;
}

.meta {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.meta-item {
  display: flex;
  align-items: center;
  gap: 6px;
  color: #666;
  font-size: 14px;
}

.meta-dates {
  display: flex;
  align-items: center;
  gap: 8px;
}

@media (min-width: 640px) {
  .meta {
    flex-direction: row;
    justify-content: space-between;
  }
}
</style>
