<template>
  <InfoCard title="文件列表">
    <div class="thumbnail-list">
      <el-row :gutter="8">
        <el-col v-for="(url, index) in urls" :key="url" :span="6" class="thumbnail-item">
          <div class="thumbnail-container">
            <el-image
              :src="url"
              :class="['thumbnail', { selected: index === currentIndex }]"
              fit="contain"
              @click="handleClick(index)"
            />
            <div class="index-badge">{{ index + 1 }}</div>
          </div>
        </el-col>
        <el-col :span="6" class="thumbnail-item">
          <div class="upload-box" @click="handleUpload">
            <el-icon :size="28">
              <Plus />
            </el-icon>
          </div>
          <input
            type="file"
            ref="fileInput"
            style="display: none"
            accept="image/*"
            @change="onFileSelected"
          />
        </el-col>
      </el-row>
    </div>
  </InfoCard>
</template>

<script setup lang="ts">
import InfoCard from './InfoCard.vue'
import { useImageViewerStore } from '@/stores/useImageViewerStore.ts'
import { storeToRefs } from 'pinia'
import { Plus } from '@element-plus/icons-vue'
import { ref } from 'vue'
import { addFile } from '@/api/file'
import { ElMessage } from 'element-plus'

const imageViewerStore = useImageViewerStore()
const {
  currentThumbnailFileUrls: urls,
  currentFileIndex: currentIndex,
  currentImageUuid,
} = storeToRefs(imageViewerStore)

const fileInput = ref<HTMLInputElement | null>(null)

function handleClick(index: number) {
  if (index === currentIndex.value) return
  imageViewerStore.setCurrentFileIndex(index)
}

function handleUpload() {
  fileInput.value?.click()
}

async function onFileSelected(event: Event) {
  const input = event.target as HTMLInputElement
  if (!input.files?.length || !currentImageUuid.value) return

  const file = input.files[0]

  await addFile(currentImageUuid.value, file)
  ElMessage.success('文件上传成功')

  await imageViewerStore.refreshCurrentImage()

  // 清空input，以便可以重复上传相同文件
  input.value = ''
}
</script>

<style scoped>
.thumbnail-list {
  width: 100%;
  height: 240px;
  padding: 12px;
  box-sizing: border-box;
  overflow-x: hidden;
  overflow-y: auto;
  background-color: var(--el-bg-color);
  border-radius: 4px;
}

.thumbnail-list :deep(.el-row) {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  margin-right: 0 !important;
  margin-left: 0 !important;
}

.thumbnail-item {
  flex: 0 0 auto;
  width: min(100px, calc(100% / 4));
  aspect-ratio: 1;
  cursor: pointer;
  margin-bottom: 8px;
  padding: 0 4px;
}

.thumbnail-container {
  position: relative;
  width: 100%;
  height: 100%;
}

.thumbnail {
  width: 100%;
  height: 100%;
  border: 2px solid transparent;
  border-radius: 4px;
  transition: var(--hover-transition);
  background-color: var(--el-bg-color-overlay);
}

.thumbnail:hover {
  border-color: var(--primary-light);
}

.thumbnail.selected {
  border-color: var(--primary-color);
}

.index-badge {
  position: absolute;
  right: 4px;
  bottom: 4px;
  background-color: rgba(0, 0, 0, 0.6);
  color: white;
  padding: 2px 6px;
  border-radius: 4px;
  font-size: 12px;
}

.upload-box {
  width: 100%;
  height: 100%;
  border: 2px dashed var(--el-border-color);
  border-radius: 4px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--el-text-color-secondary);
  transition: var(--hover-transition);
}

.upload-box:hover {
  border-color: var(--primary-color);
  color: var(--primary-color);
}
</style>
