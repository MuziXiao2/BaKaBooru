<!-- ImageViewer.vue -->
<template>
  <teleport to="body">
    <div class="image-viewer-overlay">
      <el-container class="image-viewer-container">
        <!-- 左侧预览 -->
        <el-main class="image-viewer-main">
          <el-image
            class="image-viewer-preview"
            :src="image.url"
            :alt="image.title"
            fit="contain"
            :preview-src-list="[image.url]"
            :initial-index="0"
            hide-on-click-modal
            preview-teleported
          />
        </el-main>

        <!-- 右侧信息 -->
        <el-aside class="image-viewer-aside">
          <div class="image-viewer-info">
            <h2>图片信息</h2>

            <div v-if="imageDetails" class="info-item">
              <label>UUID：</label>
              <div>{{ imageDetails.uuid }}</div>
            </div>

            <div class="info-item">
              <label>标题：</label>
              <div v-if="!editing">{{ image.title }}</div>
              <el-input v-else v-model="editableTitle" placeholder="输入标题" class="info-input" />
            </div>

            <div v-if="imageDetails" class="info-item">
              <label>创建者：</label>
              <div>{{ imageDetails.creator }}</div>
            </div>

            <div v-if="imageDetails" class="info-item">
              <label>描述：</label>
              <div>{{ imageDetails.description }}</div>
            </div>

            <div v-if="imageDetails" class="info-item">
              <label>创建时间：</label>
              <div>{{ imageDetails.createdAt }}</div>
            </div>
            <div v-if="imageDetails" class="info-item">
              <label>更新时间：</label>
              <div>{{ imageDetails.updatedAt }}</div>
            </div>
            <!--            <div v-if="imageDetails" class="info-item">-->
            <!--              <label>标签：</label>-->
            <!--              <div>{{ imageDetails.tags?.join(', ') || '无' }}</div>-->
            <!--            </div>-->
            <div v-if="!imageDetails" class="info-item">
              <div>加载详细信息中...</div>
            </div>

            <div class="info-actions">
              <el-button type="primary" @click="toggleEdit">
                {{ editing ? '保存' : '编辑' }}
              </el-button>
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
import type { ImageItem, ImageDetail } from '@/types'

const props = defineProps<{
  image: ImageItem
  imageDetails: ImageDetail | null
}>()

const emit = defineEmits<{
  (e: 'close'): void
  (e: 'update', data: { uuid: string; title: string }): void
}>()

const editing = ref(false)
const editableTitle = ref(props.image.title)

watch(
  () => props.image,
  (img) => {
    editableTitle.value = img.title
    editing.value = false
  },
)

const handleClose = () => {
  emit('close')
}

const toggleEdit = () => {
  if (editing.value) {
    emit('update', { uuid: props.image.uuid, title: editableTitle.value })
  }
  editing.value = !editing.value
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

.image-viewer-aside {
  width: 360px;
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

.info-input {
  width: 100%;
}
</style>
