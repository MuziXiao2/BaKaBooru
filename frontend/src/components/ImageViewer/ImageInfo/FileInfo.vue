<template>
  <InfoCard title="文件信息">
    <div v-if="file" class="info-grid">
      <div class="info-row" v-for="(item, index) in fileInfoList" :key="index">
        <label>{{ item.label }}：</label>
        <span>{{ item.value }}</span>
      </div>
    </div>
    <div v-else>加载文件信息中...</div>
  </InfoCard>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import InfoCard from './InfoCard.vue'
import { useImageViewerStore } from '@/stores/useImageViewerStore.ts'
import { storeToRefs } from 'pinia'

const imageViewerStore = useImageViewerStore()
const { currentFileDetail: file } = storeToRefs(imageViewerStore)

const formatFileSize = (size: number): string => {
  const units = ['B', 'KB', 'MB', 'GB']
  let value = size
  let unitIndex = 0
  while (value >= 1024 && unitIndex < units.length - 1) {
    value /= 1024
    unitIndex++
  }
  return `${value.toFixed(2)} ${units[unitIndex]}`
}

const fileInfoList = computed(() => {
  if (!file.value) return []
  return [
    { label: '文件名', value: file.value.fileName || '未知' },
    { label: '文件类型', value: file.value.type || '未知' },
    { label: '尺寸', value: `${file.value.width} x ${file.value.height} 像素` },
    { label: '文件大小', value: formatFileSize(file.value.size) },
  ]
})
</script>

<style scoped>
.info-grid {
  display: grid;
  gap: 12px;
}

.info-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 0;
}

.info-row label {
  font-weight: 600;
  color: #555;
  font-size: 14px;
}

.info-row span {
  color: #333;
  font-size: 14px;
}
</style>
