<template>
  <teleport to="body">
    <div class="image-viewer-overlay">
      <el-container class="image-viewer-container">
        <!-- 左侧预览 -->
        <el-main class="image-viewer-main">
          <el-image
            v-if="previewUrl"
            :key="previewUrl"
            class="image-viewer-preview"
            :src="previewUrl"
            :alt="selectedImageDetails?.title"
            fit="contain"
            :preview-src-list="[previewUrl]"
            :initial-index="0"
            hide-on-click-modal
            preview-teleported
            @error="handleImageError"
          />
          <div v-else class="no-image">无法加载图片</div>
        </el-main>

        <!-- 右侧信息 -->
        <el-aside class="image-viewer-aside">
          <div class="image-viewer-info">
            <h2 class="info-item">
              {{ selectedImageDetails?.title }}
            </h2>

            <!-- 文件列表 -->
            <div v-if="selectedImageDetails?.files?.length" class="info-item">
              <el-table
                :data="selectedImageDetails.files"
                highlight-current-row
                style="width: 100%"
                @current-change="handleFileRowClick"
                :row-key="(row) => row.hash"
                :current-row="currentFile"
              >
                <el-table-column prop="fileName" label="文件名" />
                <el-table-column prop="type" label="类型" />
                <el-table-column label="尺寸">
                  <template #default="{ row }"> {{ row.width }}x{{ row.height }}</template>
                </el-table-column>
                <el-table-column label="大小">
                  <template #default="{ row }">
                    {{ row.size }}
                  </template>
                </el-table-column>
              </el-table>
            </div>
            <div v-else-if="selectedImageDetails" class="info-item">
              <label>文件列表：</label>
              <div>无关联文件</div>
            </div>

            <div v-if="selectedImageDetails" class="info-item">
              <label>创建者：</label>
              <div>{{ selectedImageDetails.creator }}</div>
            </div>

            <div v-if="selectedImageDetails" class="info-item">
              <label>描述：</label>
              <div>{{ selectedImageDetails.description }}</div>
            </div>

            <div v-if="selectedImageDetails" class="info-item">
              <label>创建时间：</label>
              <div>{{ selectedImageDetails.createdAt }}</div>
            </div>
            <div v-if="selectedImageDetails" class="info-item">
              <label>更新时间：</label>
              <div>{{ selectedImageDetails.updatedAt }}</div>
            </div>

            <div v-if="selectedImageDetails" class="info-item">
              <label>UUID：</label>
              <div>{{ selectedImageDetails.uuid }}</div>
            </div>

            <div v-if="!selectedImageDetails" class="info-item">
              <div>加载详细信息中...</div>
            </div>

            <div class="info-actions">
              <el-button @click="resetPreview">恢复默认图片</el-button>
              <el-button @click="handleClose">关闭</el-button>
            </div>
          </div>
        </el-aside>
      </el-container>
    </div>
  </teleport>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue'
import { ElMessage } from 'element-plus'
import { useSelectedImageStore } from '@/stores/useSelectedImageStore'
import type { ImageFile } from '@/types'
import { storeToRefs } from 'pinia'

const selectedImageStore = useSelectedImageStore()
const { selectedImageDetails } = storeToRefs(selectedImageStore)

const previewUrl = ref('')
const currentFile = ref<ImageFile | null>(null)
const fileLoading = ref<{ [key: string]: boolean }>({})

function setDefaultPreview() {
  if (selectedImageDetails?.value?.files?.length) {
    const firstImageFile = selectedImageDetails.value.files[0]
    if (firstImageFile) {
      currentFile.value = firstImageFile
      loadPreviewUrl(firstImageFile)
      return
    }
  }
  currentFile.value = null
  previewUrl.value = ''
}

async function loadPreviewUrl(file: ImageFile) {
  try {
    fileLoading.value[file.hash] = true
    previewUrl.value = await selectedImageStore.getImageFileUrl(file.hash)
  } catch {
    ElMessage.error('无法加载文件')
    previewUrl.value = ''
  } finally {
    fileLoading.value[file.hash] = false
  }
}

watch(
  () => selectedImageDetails.value,
  () => {
    setDefaultPreview()
  },
  { immediate: true },
)

const resetPreview = () => {
  setDefaultPreview()
}

const handleImageError = () => {
  ElMessage.error('无法加载图片')
  previewUrl.value = ''
}

const handleFileRowClick = async (row: ImageFile | null) => {
  currentFile.value = row
  await loadPreviewUrl(row)
}

const handleClose = () => {
  selectedImageStore.setSelectedImage(null)
}
</script>

<style scoped>
.image-viewer-overlay {
  position: fixed;
  inset: 0;
  z-index: 9999;
  background-color: rgba(0, 0, 0, 0.85);
  overflow: hidden;
}

.image-viewer-container {
  width: 100vw;
  height: 100vh;
  display: flex;
}

.image-viewer-main {
  background: #111;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0;
}

.image-viewer-preview {
  max-width: 100%;
  max-height: 100%;
}

.no-image {
  color: #fff;
  font-size: 16px;
}

.image-viewer-aside {
  width: 20%;
  background: #fff;
  padding: 24px;

  box-shadow: -1px 0 6px rgba(0, 0, 0, 0.1);
  position: relative;
  display: flex;
  flex-direction: column;
}

.image-viewer-info {
  flex: 1;
  overflow-y: auto;
  padding-bottom: 72px;
}

.info-actions {
  position: absolute;
  bottom: 24px;
  right: 24px;
  display: flex;
  gap: 12px;
}

.image-viewer-info h2 {
  margin-bottom: 24px;
  font-size: 20px;
}

.info-item {
  font-size: 16px;
  margin-bottom: 24px;
}

.info-item label {
  font-weight: 600;
  margin-right: 8px;
}
</style>
