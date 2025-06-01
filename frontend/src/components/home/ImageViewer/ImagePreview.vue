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
  background-color: var(--el-bg-color-overlay);
}

.image-viewer-preview {
  max-width: calc(100% - 120px);
  max-height: 100%;
  width: auto;
  height: auto;
  transition: all 0.3s ease;
}

.no-image {
  color: var(--el-text-color-regular);
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
  padding: 0 32px;
  pointer-events: none;
  z-index: 1000;
}

.nav-arrow {
  background-color: var(--el-bg-color);
  border: 1px solid var(--el-border-color-light);
  color: var(--el-text-color-regular);
  width: 40px;
  height: 40px;
  border-radius: 50%;
  box-shadow: var(--el-box-shadow);
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  pointer-events: auto;
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0.9;
}

.nav-arrow:hover {
  background-color: var(--el-bg-color);
  color: var(--primary-color);
  border-color: var(--primary-color);
  box-shadow: 0 4px 16px rgba(var(--primary-color), 0.2);
  transform: scale(1.05) translateX(0);
  opacity: 1;
}

.nav-arrow.left:hover {
  transform: scale(1.05) translateX(-4px);
}

.nav-arrow.right:hover {
  transform: scale(1.05) translateX(4px);
}

.nav-arrow:disabled {
  background-color: var(--el-bg-color-overlay);
  color: var(--el-text-color-secondary);
  border-color: transparent;
  box-shadow: none;
  cursor: not-allowed;
  opacity: 0.5;
  transform: none;
}
</style>
