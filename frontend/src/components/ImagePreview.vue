<template>
  <div class="image-preview-container">
    <!-- 大图预览 -->
    <div class="main-preview" v-loading="loading">
      <el-image
        v-if="previewUrl"
        :key="previewUrl"
        class="image-viewer-preview"
        :src="previewUrl"
        :alt="`图片 ${currentIndex + 1}`"
        fit="contain"
        :preview-src-list="[previewUrl]"
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
          :disabled="currentIndex === 0 || loading"
          @click="navigateImage(-1)"
          :icon="ArrowLeft"
        />
        <el-button
          v-if="files.length > 1"
          class="nav-arrow right"
          circle
          :disabled="currentIndex === files.length - 1 || loading"
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
            :src="previewUrlCache[file.hash] || ''"
            :class="{ 'thumbnail-image': true, selected: index === currentIndex }"
            fit="cover"
            v-loading="thumbnailLoading[file.hash] || false"
            @click="selectImage(index)"
            @error="handleThumbnailError(file.hash)"
          />
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue'
import { storeToRefs } from 'pinia'
import { ElMessage } from 'element-plus'
import { ArrowLeft, ArrowRight } from '@element-plus/icons-vue'
import { useCurrentImageStore } from '@/stores/useSelectedImageStore'
import type { FileDetail } from '@/types'

const props = defineProps<{
  currentIndex: number
}>()

const emit = defineEmits<{
  (e: 'update:current-index', index: number): void
  (e: 'update:preview-url', url: string): void
  (e: 'update:current-file', file: FileDetail | null): void
}>()

const currentImageStore = useCurrentImageStore()
const { currentFileDetails: files, currentImageDetail: file } = storeToRefs(currentImageStore)

const previewUrl = ref('')
const loading = ref(false)
const previewUrlCache = ref<{ [key: string]: string }>({})
const thumbnailLoading = ref<{ [key: string]: boolean }>({})

async function loadPreviewUrl() {
  const hash = file.value?.hash
  if (previewUrlCache.value[hash]) {
    previewUrl.value = previewUrlCache.value[hash]
    emit('update:preview-url', previewUrl.value)
    return
  }
  try {
    loading.value = true
    thumbnailLoading.value[hash] = true
    const url = await currentImageStore.getImageFileUrl(hash)
    previewUrlCache.value[hash] = url
    previewUrl.value = url
    emit('update:preview-url', url)
  } catch {
    ElMessage.error('无法加载图片')
    previewUrl.value = ''
    previewUrlCache.value[hash] = ''
    emit('update:preview-url', '')
  } finally {
    loading.value = false
    thumbnailLoading.value[hash] = false
  }
}

const navigateImage = (direction: number) => {
  const newIndex = (props.currentIndex + direction + files.value.length) % files.value.length
  emit('update:current-index', newIndex)
  file.value = files.value[newIndex]
  loadPreviewUrl()
}

const selectImage = (index: number) => {
  emit('update:current-index', index)
  const newFile = files.value[index]
  emit('update:current-file', newFile)
  loadPreviewUrl(newFile)
}

const handleImageError = () => {
  ElMessage.error('无法加载图片')
  previewUrl.value = ''
  emit('update:preview-url', '')
}

const handleThumbnailError = (hash: string) => {
  previewUrlCache.value[hash] = ''
  thumbnailLoading.value[hash] = false
}

// 预加载所有图片 URL
watch(
  () => files.value,
  async (newFiles) => {
    for (const file of newFiles) {
      if (!previewUrlCache.value[file.hash] && !thumbnailLoading.value[file.hash]) {
        try {
          thumbnailLoading.value[file.hash] = true
          const url = await currentImageStore.getImageFileUrl(file.hash)
          previewUrlCache.value[file.hash] = url
        } catch {
          previewUrlCache.value[file.hash] = ''
        } finally {
          thumbnailLoading.value[file.hash] = false
        }
      }
    }
  },
  { immediate: true },
)

// 初始化当前图片
watch(
  () => props.currentIndex,
  (newIndex) => {
    if (files.value[newIndex]) {
      const file = files.value[newIndex]
      emit('update:current-file', file)
      loadPreviewUrl(file)
    }
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
