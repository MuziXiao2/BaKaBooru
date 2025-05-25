<template>
  <div class="image-viewer-info">
    <!-- 标题和创建者 -->
    <div class="header-section">
      <h2 class="image-title">{{ currentImageDetail?.title || '未命名图片' }}</h2>
      <div class="creator">创建者: {{ currentImageDetail?.creator || '未知' }}</div>
    </div>

    <!-- 标签区域 -->
    <div class="tags-section info-item">
      <h3>标签</h3>
      <div class="tags-container"></div>
    </div>

    <!-- 图片信息模块 -->
    <div v-if="currentImageDetail && currentFileDetail" class="info-item image-info-module">
      <h3>图片信息</h3>
      <div class="info-grid">
        <div class="info-row">
          <label>文件名：</label>
          <span>{{ currentFileDetail.fileName || '未知' }}</span>
        </div>
        <div class="info-row">
          <label>文件类型：</label>
          <span>{{ currentFileDetail.type || '未知' }}</span>
        </div>
        <div class="info-row">
          <label>尺寸：</label>
          <span>{{ currentFileDetail.width }} x {{ currentFileDetail.height }} 像素</span>
        </div>
        <div class="info-row">
          <label>文件大小：</label>
          <span>{{ formatFileSize(currentFileDetail.size) }}</span>
        </div>
        <div class="info-row">
          <label>创建时间：</label>
          <span>{{ currentImageDetail.createdAt || '未知' }}</span>
        </div>
        <div class="info-row">
          <label>更新时间：</label>
          <span>{{ currentImageDetail.updatedAt || '未知' }}</span>
        </div>
      </div>
    </div>
    <div v-else class="info-item">
      <div>加载图片信息中...</div>
    </div>

    <!-- 操作按钮 -->
    <div class="info-actions">
      <el-button @click="$emit('close')">关闭</el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { storeToRefs } from 'pinia'
import { useCurrentImageStore } from '@/stores/useCurrentImageStore.ts'

const currentImageStore = useCurrentImageStore()
const { currentImageDetail, currentFileDetail } = storeToRefs(currentImageStore)

function formatFileSize(size: number | undefined): string {
  if (!size) return '未知'
  const units = ['B', 'KB', 'MB', 'GB']
  let value = size
  let unitIndex = 0
  while (value >= 1024 && unitIndex < units.length - 1) {
    value /= 1024
    unitIndex++
  }
  return `${value.toFixed(2)} ${units[unitIndex]}`
}
</script>

<style scoped>
.image-viewer-info {
  flex: 1;
  padding-bottom: 80px;
  overflow-y: auto;
}

.header-section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
  padding-bottom: 16px;
  border-bottom: 1px solid #eee;
}

.image-title {
  font-size: 24px;
  font-weight: 700;
  color: #333;
  margin: 0;
  letter-spacing: 0.5px;
}

.creator {
  font-size: 14px;
  color: #666;
  font-weight: 400;
}

.tags-section {
  padding: 16px;
  border-radius: 8px;
  background: #f8f8f8;
  margin: 16px auto;
}

.tags-section h3 {
  font-size: 18px;
  color: #333;
  margin: 0 0 16px 0;
  font-weight: 600;
}

.tags-container {
  min-height: 32px;
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.no-tags {
  color: #999;
  font-size: 14px;
}

.image-info-module {
  padding: 16px;
  border-radius: 8px;
  background: #f8f8f8;
  margin: 0 auto;
}

.image-info-module h3 {
  font-size: 18px;
  color: #333;
  margin: 0 0 16px 0;
  font-weight: 600;
}

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

.info-actions {
  position: absolute;
  bottom: 24px;
  right: 24px;
  display: flex;
  gap: 12px;
}

.info-actions .el-button {
  border-radius: 6px;
  padding: 10px 20px;
}
</style>
