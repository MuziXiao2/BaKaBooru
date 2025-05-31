<template>
  <InfoCard title="图片详情">
    <h2 class="title">{{ currentImageDetail?.title || '未命名图片' }}</h2>
    <div class="meta">
      <div class="meta-item">
        <el-icon>
          <User />
        </el-icon>
        <span>{{ currentImageDetail?.creator || '未知' }}</span>
      </div>
      <div class="meta-dates">
        <div class="meta-item">
          <el-icon>
            <Clock />
          </el-icon>
          <span>创建于 {{ currentImageDetail?.createdAt || '未知' }}</span>
        </div>
        <div class="meta-item">
          <el-icon>
            <Clock />
          </el-icon>
          <span>更新于 {{ currentImageDetail?.updatedAt || '未知' }}</span>
        </div>
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
const { currentImageDetail } = storeToRefs(imageViewerStore)
</script>

<style scoped>
.title {
  font-size: 24px;
  font-weight: 600;
  color: var(--el-text-color-primary);
  margin: 0 0 16px 0;
  line-height: 1.4;
  word-break: break-word;
}

.meta {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.meta-item {
  display: flex;
  align-items: center;
  gap: 8px;
  color: var(--el-text-color-regular);
  font-size: 14px;
  padding: 4px 8px;
  background-color: var(--el-bg-color);
  border-radius: 6px;
  transition: var(--hover-transition);
}

.meta-item:hover {
  background-color: var(--el-bg-color-overlay);
  transform: translateX(4px);
}

.meta-item .el-icon {
  color: var(--primary-color);
  font-size: 16px;
}

.meta-dates {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

@media (min-width: 640px) {
  .meta {
    flex-direction: row;
    justify-content: space-between;
    align-items: flex-start;
  }

  .meta-dates {
    text-align: right;
  }
}
</style>
