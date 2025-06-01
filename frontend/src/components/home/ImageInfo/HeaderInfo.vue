<template>
  <InfoCard title="图片信息">
    <h2 class="title">{{ currentImageDetail?.title || '未命名图片' }}</h2>
    <div class="meta">
      <div class="meta-item">
        <el-icon>
          <User />
        </el-icon>
        <span>{{ currentImageDetail?.creator || '未知' }}</span>
      </div>
      <div class="meta-item">
        <el-icon>
          <View />
        </el-icon>
        <span>查看次数：{{ currentImageDetail?.viewCount || '未知' }}</span>
      </div>
      <div class="meta-item">
        <el-icon>
          <Clock />
        </el-icon>
        <span>创建于 {{ currentImageDetail?.createdAt || '未知' }}</span>
      </div>
      <div class="meta-item">
        <el-icon>
          <Clock />
        </el-icon>
        <span>更新于 {{ currentImageDetail?.updatedAt || '未知' }}</span>
      </div>
    </div>
    <div class="description-wrapper">
      <div class="description-header" v-show="isExpanded">
        <el-icon v-if="!isEditing" class="edit-button" @click="startEdit">
          <Edit />
        </el-icon>
      </div>
      <div v-if="!isEditing" class="description-content" v-show="isExpanded">
        {{ currentImageDetail?.description || '暂无描述' }}
      </div>
      <el-input
        v-else
        v-model="editingDescription"
        type="textarea"
        :rows="4"
        placeholder="请输入描述"
        resize="none"
      />
      <div v-if="isEditing" class="edit-actions">
        <el-button type="primary" size="small" @click="saveDescription">保存</el-button>
        <el-button size="small" @click="cancelEdit">取消</el-button>
      </div>
      <div
        v-if="currentImageDetail?.description && !isEditing"
        class="expand-button"
        @click="toggleExpand"
      >
        <span>{{ isExpanded ? '收起描述' : '展开描述' }}</span>
        <el-icon class="expand-icon" :class="{ 'is-expanded': isExpanded }">
          <ArrowDown />
        </el-icon>
      </div>
    </div>
  </InfoCard>
</template>

<script setup lang="ts">
import { Clock, User, View, ArrowDown, Edit } from '@element-plus/icons-vue'
import InfoCard from './InfoCard.vue'
import { useImageViewerStore } from '@/stores/useImageViewerStore.ts'
import { storeToRefs } from 'pinia'
import { ref } from 'vue'
import { updateImageDescription } from '@/api/image.ts'

const imageViewerStore = useImageViewerStore()
const { currentImageDetail } = storeToRefs(imageViewerStore)
const isExpanded = ref(false)
const isEditing = ref(false)
const editingDescription = ref('')

const toggleExpand = () => {
  isExpanded.value = !isExpanded.value
}

const startEdit = () => {
  editingDescription.value = currentImageDetail.value?.description || ''
  isEditing.value = true
}

const cancelEdit = () => {
  isEditing.value = false
  editingDescription.value = ''
}

const saveDescription = async () => {
  if (!currentImageDetail.value?.uuid) return
  await updateImageDescription(currentImageDetail.value.uuid, editingDescription.value)
  currentImageDetail.value.description = editingDescription.value
  isEditing.value = false
}
</script>

<style scoped>
.title {
  font-size: 24px;
  font-weight: 600;
  color: var(--el-text-color-primary);
  margin: 0 0 16px 0;
  line-height: 1.4;
  word-break: break-word;
}

.meta {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 12px;
  width: 100%;
}

@media (max-width: 768px) {
  .meta {
    grid-template-columns: 1fr;
  }
}

.meta-item {
  display: flex;
  align-items: center;
  gap: 8px;
  color: var(--el-text-color-regular);
  font-size: 14px;
  padding: 8px 12px;
  background-color: var(--el-bg-color);
  border-radius: 6px;
  transition: var(--hover-transition);
}

.meta-item:hover {
  background-color: var(--el-bg-color-overlay);
  transform: translateY(-2px);
}

.meta-item .el-icon {
  color: var(--primary-color);
  font-size: 16px;
}

.description-wrapper {
  margin-top: 20px;
  position: relative;
}

.description-header {
  position: absolute;
  right: 8px;
  top: 8px;
  z-index: 1;
}

.edit-button {
  opacity: 0.7;
  transition: opacity 0.3s;
  cursor: pointer;
  color: var(--primary-color);
  font-size: 16px;
}

.edit-button:hover {
  opacity: 1;
}

.edit-actions {
  display: flex;
  gap: 8px;
  justify-content: flex-end;
  margin-top: 8px;
}

.description-content {
  font-size: 14px;
  line-height: 1.6;
  color: var(--el-text-color-regular);
  white-space: pre-wrap;
  word-break: break-word;
  transition: all 0.3s ease;
  padding: 12px 16px;
  padding-right: 40px;
  background-color: var(--el-bg-color);
  border-radius: 6px;
  margin-bottom: 8px;
}

.expand-button {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 4px;
  margin-top: 8px;
  cursor: pointer;
  color: var(--primary-color);
  font-size: 14px;
  transition: var(--hover-transition);
}

.expand-button:hover {
  opacity: 0.8;
}

.expand-icon {
  transition: transform 0.3s ease;
}

.expand-icon.is-expanded {
  transform: rotate(180deg);
}
</style>
