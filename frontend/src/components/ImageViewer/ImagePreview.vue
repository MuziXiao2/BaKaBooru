<template>
  <div class="image-preview-container">
    <!-- 预览图-->
    <div class="main-preview" v-loading="loading">
      <el-image
        v-if="url"
        :key="url"
        class="image-viewer-preview"
        :src="url"
        :alt="`图片 ${imageViewerStore.currentFileIndex + 1}`"
        fit="contain"
        :preview-src-list="[url]"
        :initial-index="0"
        hide-on-click-modal
        preview-teleported
        lazy
        show-progress
        :z-index="1001"
      />
      <div v-else class="no-image">无法加载图片</div>

      <!-- 左右箭头 -->
      <div class="navigation-arrows">
        <el-button
          v-if="files.length > 1"
          class="nav-arrow left"
          circle
          :disabled="isFirstImage || loading"
          @click="navigateImage(-1)"
          :icon="ArrowLeft"
        />
        <el-button
          v-if="files.length > 1"
          class="nav-arrow right"
          circle
          :disabled="isLastImage || loading"
          @click="navigateImage(1)"
          :icon="ArrowRight"
        />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { storeToRefs } from 'pinia'
import { ArrowLeft, ArrowRight } from '@element-plus/icons-vue'
import { useImageViewerStore } from '@/stores/useImageViewerStore.ts'

const imageViewerStore = useImageViewerStore()
const { currentFileDetails: files, currentFileUrl: url } = storeToRefs(imageViewerStore)

const loading = ref(false)
const isFirstImage = computed(() => imageViewerStore.isFirstFile)
const isLastImage = computed(() => imageViewerStore.isLastFile)

const navigateImage = async (direction: number) => {
  if (direction < 0) await imageViewerStore.previousFile()
  else await imageViewerStore.nextFile()
}
</script>

<style scoped>
.image-preview-container {
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 100%;
  overflow: hidden;
}

.main-preview {
  flex: 1;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  overflow: hidden;
  background: #f5f7fa; /* 添加背景色 */
}

.image-viewer-preview {
  max-width: calc(100% - 120px); /* 为按钮留出空间 */
  max-height: 100%;
  width: auto;
  height: auto;
  transition: all 0.3s ease;
}

.no-image {
  color: #fff;
  font-size: 16px;
}

.navigation-arrows {
  position: absolute;
  top: 50%;
  left: 0;
  right: 0;
  display: flex;
  justify-content: space-between;
  transform: translateY(-50%);
  padding: 0 32px; /* 增加水平内边距 */
  pointer-events: none;
  z-index: 1000;
}

.nav-arrow {
  background: rgba(255, 255, 255, 0.8); /* 增加背景不透明度 */
  border: 1px solid rgba(0, 0, 0, 0.1);
  color: #606266;
  width: 40px; /* 稍微减小按钮尺寸 */
  height: 40px;
  border-radius: 50%;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  pointer-events: auto;
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0.9; /* 默认稍微透明 */
}

.nav-arrow:hover {
  background: #ffffff;
  color: #409eff;
  border-color: #409eff;
  box-shadow: 0 4px 16px rgba(64, 158, 255, 0.2);
  transform: scale(1.05) translateX(0);
  opacity: 1;
}

.nav-arrow.left:hover {
  transform: scale(1.05) translateX(-4px); /* 悬停时轻微向左移动 */
}

.nav-arrow.right:hover {
  transform: scale(1.05) translateX(4px); /* 悬停时轻微向右移动 */
}

.nav-arrow:disabled {
  background: rgba(255, 255, 255, 0.5);
  color: #909399;
  border-color: transparent;
  box-shadow: none;
  cursor: not-allowed;
  opacity: 0.5;
  transform: none;
}
</style>
