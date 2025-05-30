<script setup lang="ts">
import { useImageViewerStore } from '@/stores/useImageViewerStore'
import { storeToRefs } from 'pinia'
import InfoCard from './InfoCard.vue'

const store = useImageViewerStore()
const { currentImageTags } = storeToRefs(store)
</script>

<template>
  <InfoCard title="标签">
    <div class="tag-list">
      <div class="tag-list-content">
        <span v-for="tag in currentImageTags" :key="tag" class="tag">
          {{ tag }}
        </span>
      </div>
      <div class="tag-count">共 {{ currentImageTags.length }} 个标签</div>
    </div>
  </InfoCard>
</template>

<style scoped>
.tag-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.tag-list-content {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.tag {
  padding: 4px 12px;
  background-color: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
  border-radius: 4px;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.tag:hover {
  background-color: var(--el-color-primary-light-8);
  transform: translateY(-1px);
}

.tag-count {
  font-size: 14px;
  color: var(--el-text-color-secondary);
}
</style>
