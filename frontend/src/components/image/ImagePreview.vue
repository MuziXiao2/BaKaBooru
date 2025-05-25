<template>
  <div class="image-preview-container">
    <!-- 大图预览 -->
    <div class="main-preview" v-loading="loading">
      <el-image
        v-if="currentImageUrl"
        :key="currentImageUrl"
        class="image-viewer-preview"
        :src="currentImageUrl"
        :alt="`图片 ${currentImageStore.currentFileIndex + 1}`"
        fit="contain"
        :preview-src-list="[currentImageUrl]"
        :initial-index="0"
        hide-on-click-modal
        preview-teleported
        @error="handleImageError"
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

    <!-- 预览小图 -->
    <div class="thumbnail-list">
      <el-row :gutter="8">
        <el-col v-for="(file, index) in files" :key="file.hash" :span="4" class="thumbnail-item">
          <el-image
            :src="getThumbnailUrl(file.hash)"
            :class="{
              'thumbnail-image': true,
              selected: index === currentImageStore.currentFileIndex,
            }"
            fit="cover"
            v-loading="isThumbnailLoading(file.hash)"
            @click="selectImage(index)"
            @error="handleThumbnailError(file.hash)"
          />
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup lang="ts">
import { watch, ref, computed } from 'vue'
import { storeToRefs } from 'pinia'
import { ElMessage } from 'element-plus'
import { ArrowLeft, ArrowRight } from '@element-plus/icons-vue'
import { useCurrentImageStore } from '@/stores/useCurrentImageStore.ts'
import type { FileDetail } from '@/types'

// Store
const currentImageStore = useCurrentImageStore()
const { currentFileDetails: files } = storeToRefs(currentImageStore)

// State
const loading = ref(false)
const currentImageUrl = ref<string>('')
const previewUrlCache = ref<Map<string, string>>(new Map())
const thumbnailLoading = ref<Map<string, boolean>>(new Map())

// Computed
const isFirstImage = computed(() => currentImageStore.isFirstFile)
const isLastImage = computed(() => currentImageStore.isLastFile)

// Methods
const getThumbnailUrl = (hash: string): string => {
  return previewUrlCache.value.get(hash) || ''
}

const isThumbnailLoading = (hash: string): boolean => {
  return thumbnailLoading.value.get(hash) || false
}

const loadImageUrl = async (hash: string): Promise<string> => {
  if (!hash) return ''

  if (previewUrlCache.value.has(hash)) {
    return previewUrlCache.value.get(hash) || ''
  }

  try {
    thumbnailLoading.value.set(hash, true)
    const url = await currentImageStore.getImageFileUrl(hash)
    previewUrlCache.value.set(hash, url)
    return url
  } catch (e) {
    console.error('加载图片失败:', e)
    ElMessage.error('无法加载图片')
    return ''
  } finally {
    thumbnailLoading.value.set(hash, false)
  }
}

const updateCurrentFile = async (file: FileDetail | null) => {
  if (!file) {
    currentImageUrl.value = ''
    return
  }

  if (loading.value) return // 避免重复加载

  loading.value = true
  try {
    const url = await loadImageUrl(file.hash)
    if (url) {
      currentImageUrl.value = url
    } else {
      currentImageUrl.value = ''
      ElMessage.error('无法加载图片')
    }
  } catch (e) {
    console.error('更新当前文件失败:', e)
    currentImageUrl.value = ''
    ElMessage.error('无法加载图片')
  } finally {
    loading.value = false
  }
}

const selectImage = async (index: number) => {
  await currentImageStore.setCurrentFileIndex(index)
  const file = currentImageStore.currentFile
  if (file) {
    await updateCurrentFile(file)
  }
}

const navigateImage = async (direction: number) => {
  if (direction < 0) {
    await currentImageStore.previousFile()
  } else {
    await currentImageStore.nextFile()
  }
  const file = currentImageStore.currentFile
  if (file) {
    await updateCurrentFile(file)
  }
}

const handleImageError = () => {
  ElMessage.error('无法加载图片')
  currentImageUrl.value = ''
}

const handleThumbnailError = (hash: string) => {
  previewUrlCache.value.delete(hash)
  thumbnailLoading.value.delete(hash)
}

// 预加载所有缩略图
const preloadThumbnails = async () => {
  for (const file of files.value) {
    if (!previewUrlCache.value.has(file.hash)) {
      await loadImageUrl(file.hash)
    }
  }
}

// 监听文件列表变化，预加载缩略图
watch(() => files.value, preloadThumbnails, { immediate: true })

// 监听当前索引变化，更新当前图片
watch(
  () => currentImageStore.currentFileIndex,
  () => {
    const file = currentImageStore.currentFile
    updateCurrentFile(file)
  },
  { immediate: true },
)
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
  max-height: calc(100% - 100px); /* 留出小图列表空间 */
  overflow: hidden;
}

.image-viewer-preview {
  max-width: 100%;
  max-height: 100%;
  width: auto;
  height: auto;
  transition: opacity 0.3s ease;
}

.no-image {
  color: #fff;
  font-size: 16px;
}

.navigation-arrows {
  position: absolute;
  top: 50%;
  width: 100%;
  display: flex;
  justify-content: space-between;
  transform: translateY(-50%);
  padding: 0 20px;
  pointer-events: none;
}

.nav-arrow {
  background: rgba(255, 255, 255, 0.5);
  border: none;
  color: #000;
  font-size: 32px;
  width: 48px;
  height: 48px;
  line-height: 48px;
  border-radius: 50%;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
  transition: all 0.3s ease;
  pointer-events: auto;
  display: flex;
  align-items: center;
  justify-content: center;
}

.nav-arrow:hover {
  background: #ffffff;
  border: 2px solid #409eff;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
  transform: scale(1.1);
}

.nav-arrow:disabled {
  background: rgba(255, 255, 255, 0.3);
  color: #666;
  border: none;
  cursor: not-allowed;
  transform: none;
}

.thumbnail-list {
  height: 100px;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 10px;
  box-sizing: border-box;
  overflow-x: auto;
  overflow-y: hidden;
}

.thumbnail-list .el-row {
  display: flex;
  flex-wrap: nowrap;
  width: 100%;
  justify-content: center;
}

.thumbnail-item {
  flex: 0 0 auto;
  width: min(80px, calc(100% / 6));
  height: 80px;
  cursor: pointer;
}

.thumbnail-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border: 2px solid transparent;
  border-radius: 4px;
  transition: all 0.3s ease;
}

.thumbnail-image:hover {
  border-color: #66b1ff;
}

.thumbnail-image.selected {
  border-color: #409eff;
}
</style>
