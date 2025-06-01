<template>
  <InfoCard title="标签">
    <template #extra>
      <el-icon class="add-icon" @click="handleAddTag">
        <Plus />
      </el-icon>
    </template>
    <div class="tag-list">
      <div class="tag-list-content">
        <span v-for="tag in currentImageTags" :key="tag" class="tag">
          {{ tag }}
        </span>
      </div>
      <div class="tag-count">共 {{ currentImageTags.length }} 个标签</div>
    </div>
  </InfoCard>
</template>

<script setup lang="ts">
import { useImageViewerStore } from '@/stores/useImageViewerStore.ts'
import { storeToRefs } from 'pinia'
import InfoCard from './InfoCard.vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import { addTags } from '@/api/tag'
import 'element-plus/es/components/message/style/css'
import 'element-plus/es/components/message-box/style/css'

const store = useImageViewerStore()
const { currentImageTags, currentImageDetail } = storeToRefs(store)

const handleAddTag = async () => {
  const tag = await ElMessageBox.prompt('请输入标签', '添加标签', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    inputPattern: /\S+/,
    inputErrorMessage: '标签不能为空',
  })

  if (tag.value && currentImageDetail.value?.uuid) {
    const newTags = await addTags(currentImageDetail.value.uuid, tag.value)
    store.$patch({ currentImageTags: newTags })
    ElMessage.success('添加标签成功')
  }
}
</script>

<style scoped>
.tag-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.tag-list-content {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.tag {
  padding: 6px 12px;
  background-color: var(--el-bg-color-overlay);
  color: var(--el-text-color-regular);
  border: 1px solid var(--el-border-color-light);
  border-radius: 6px;
  font-size: 14px;
  cursor: pointer;
  transition: var(--hover-transition);
  user-select: none;
}

.tag:hover {
  background-color: var(--primary-color);
  color: white;
  border-color: var(--primary-color);
  transform: translateY(-2px);
  box-shadow: 0 2px 8px rgba(58, 123, 213, 0.2);
}

.tag-count {
  font-size: 14px;
  color: var(--el-text-color-secondary);
  padding: 4px 0;
  border-top: 1px solid var(--el-border-color-light);
  margin-top: 8px;
}

.add-icon {
  font-size: 18px;
  color: var(--el-text-color-secondary);
  cursor: pointer;
  transition: var(--hover-transition);
}

.add-icon:hover {
  color: var(--primary-color);
  transform: scale(1.1);
}
</style>
