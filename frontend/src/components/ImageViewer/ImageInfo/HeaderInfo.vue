<template>
  <InfoCard title="图片信息">
    <h2 class="title">{{ currentImageDetail?.title || '未命名图片' }}</h2>
    <div class="meta">
      <div class="meta-item">
        <el-icon>
          <User />
        </el-icon>
        <span>{{ currentImageDetail?.creator || '未知' }}</span>
      </div>
      <div class="meta-item">
        <el-icon>
          <View />
        </el-icon>
        <span>查看次数：{{ currentImageDetail?.viewCount || '未知' }}</span>
      </div>
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
    <div class="description-wrapper">
      <div class="description-content" :class="{ 'collapsed': !isExpanded }">
        {{ currentImageDetail?.description || '暂无描述' }}
      </div>
      <div v-if="currentImageDetail?.description" class="expand-button" @click="toggleExpand">
        {{ isExpanded ? '收起' : '展开' }}
        <el-icon class="expand-icon" :class="{ 'is-expanded': isExpanded }">
          <ArrowDown />
        </el-icon>
      </div>
    </div>
  </InfoCard>
</template>

<script setup lang="ts">
import { Clock, User, View, ArrowDown } from '@element-plus/icons-vue'
import InfoCard from './InfoCard.vue'
import { useImageViewerStore } from '@/stores/useImageViewerStore.ts'
import { storeToRefs } from 'pinia'
import { ref } from 'vue'

const imageViewerStore = useImageViewerStore()
const { currentImageDetail } = storeToRefs(imageViewerStore)
const isExpanded = ref(false)

const toggleExpand = () => {
  isExpanded.value = !isExpanded.value
}
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
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 12px;
}

.meta-item {
  display: flex;
  align-items: center;
  gap: 8px;
  color: var(--el-text-color-regular);
  font-size: 14px;
  padding: 8px 12px;
  background-color: var(--el-bg-color);
  border-radius: 6px;
  transition: var(--hover-transition);
}

.meta-item:hover {
  background-color: var(--el-bg-color-overlay);
  transform: translateY(-2px);
}

.meta-item .el-icon {
  color: var(--primary-color);
  font-size: 16px;
}

@media (max-width: 640px) {
  .meta {
    grid-template-columns: 1fr;
  }
}

.description-wrapper {
  margin-top: 20px;
}

.description-content {
  font-size: 14px;
  line-height: 1.6;
  color: var(--el-text-color-regular);
  white-space: pre-wrap;
  word-break: break-word;
  transition: max-height 0.3s ease;
  overflow: hidden;
  padding: 12px 16px;
  background-color: var(--el-bg-color);
  border-radius: 6px;
}

.description-content.collapsed {
  max-height: 100px;
  position: relative;
  mask-image: linear-gradient(to bottom, black 60%, transparent 100%);
}

.expand-button {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 4px;
  margin-top: 8px;
  cursor: pointer;
  color: var(--primary-color);
  font-size: 14px;
  transition: var(--hover-transition);
}

.expand-button:hover {
  opacity: 0.8;
}

.expand-icon {
  transition: transform 0.3s ease;
}

.expand-icon.is-expanded {
  transform: rotate(180deg);
}
</style>
