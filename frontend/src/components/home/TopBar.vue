<template>
  <div class="top-bar">
    <div class="left">
      <el-breadcrumb :separator-icon="ArrowRight" class="breadcrumb">
        <el-breadcrumb-item :to="{ path: '/' }">BaKaBooru</el-breadcrumb-item>
        <el-breadcrumb-item>{{ route.meta.title }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="right">
      <el-button
        class="action-btn"
        :icon="Plus"
        @click="showAddImageDialog"
      />
      <el-button
        class="action-btn"
        :class="{ 'is-active': isGalleryPage }"
        :icon="HomeFilled"
        @click="goToGallery"
      />
      <el-button
        class="action-btn"
        :class="{ 'is-active': isSettingPage }"
        :icon="Setting"
        @click="goToSetting"
      />
      <el-button
        class="action-btn"
        :class="{ 'is-active': isDark }"
        :icon="isDark ? Sunny : Moon"
        @click="toggleDark"
      />
    </div>
  </div>

  <!-- 添加图片对话框 -->
  <el-dialog
    v-model="addImageDialogVisible"
    title="添加图片"
    width="500px"
  >
    <el-form :model="imageForm" label-width="80px">
      <el-form-item label="标题">
        <el-input v-model="imageForm.title" placeholder="请输入标题" />
      </el-form-item>
      <el-form-item label="创建者">
        <el-input v-model="imageForm.creator" placeholder="请输入创建者" />
      </el-form-item>
      <el-form-item label="描述">
        <el-input
          v-model="imageForm.description"
          type="textarea"
          rows="3"
          placeholder="请输入描述"
        />
      </el-form-item>
      <el-form-item label="图片文件">
        <el-upload
          v-model:file-list="fileList"
          :auto-upload="false"
          :multiple="true"
          accept="image/*"
        >
          <el-button type="primary">选择图片</el-button>
          <template #tip>
            <div class="el-upload__tip">
              请至少上传一张图片文件
            </div>
          </template>
        </el-upload>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addImageDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleAddImage" :loading="isSubmitting">
          创建
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ArrowRight, Setting, Moon, Sunny, HomeFilled, Plus } from '@element-plus/icons-vue'
import { useRoute, useRouter } from 'vue-router'
import { ref, computed, onMounted } from 'vue'
import { addImage, addFile } from '@/api'
import { useImageViewerStore } from '@/stores/useImageViewerStore'
import { useImageStore } from '@/stores/useImageStore'
import { ElMessage } from 'element-plus'
import type { UploadUserFile } from 'element-plus'

const route = useRoute()
const router = useRouter()
const isDark = ref(false)

const isSettingPage = computed(() => route.name === 'setting')
const isGalleryPage = computed(() => route.name === 'gallery')

const toggleDark = () => {
  isDark.value = !isDark.value
  if (isDark.value) {
    document.documentElement.classList.add('dark')
    document.body.setAttribute('class', 'dark')
  } else {
    document.documentElement.classList.remove('dark')
    document.body.removeAttribute('class')
  }
  localStorage.setItem('theme', isDark.value ? 'dark' : 'light')
}

onMounted(() => {
  // 检查本地存储的主题设置
  const savedTheme = localStorage.getItem('theme')
  // 检查系统偏好
  const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches

  if (savedTheme === 'dark' || (savedTheme === null && prefersDark)) {
    isDark.value = true
    document.documentElement.classList.add('dark')
    document.body.setAttribute('class', 'dark')
  }
})

const goToSetting = () => {
  router.push({ name: 'setting' })
}

const goToGallery = () => {
  router.push({ name: 'gallery' })
}

const addImageDialogVisible = ref(false)
const isSubmitting = ref(false)
const imageForm = ref({
  title: '',
  creator: '',
  description: ''
})
const fileList = ref<UploadUserFile[]>([])
const imageViewerStore = useImageViewerStore()
const imageStore = useImageStore()

const showAddImageDialog = () => {
  addImageDialogVisible.value = true
  imageForm.value = {
    title: '',
    creator: '',
    description: ''
  }
  fileList.value = []
}

const handleAddImage = async () => {
  if (!fileList.value.length) {
    ElMessage.warning('请至少上传一张图片')
    return
  }

  try {
    isSubmitting.value = true

    // 创建图片记录
    const imageDetail = await addImage({
      title: imageForm.value.title,
      creator: imageForm.value.creator,
      description: imageForm.value.description
    })

    // 上传所有图片文件
    for (const file of fileList.value) {
      if (file.raw) {
        await addFile(imageDetail.uuid, file.raw)
      }
    }

    // 关闭对话框
    addImageDialogVisible.value = false

    // 打开图片查看器
    await imageViewerStore.openImageViewer(imageDetail.uuid)

    // 刷新图片列表
    imageStore.queryImages()

    ElMessage.success('图片添加成功')
  } catch (error) {
    ElMessage.error('图片添加失败')
    console.error(error)
  } finally {
    isSubmitting.value = false
  }
}
</script>

<style scoped>
.top-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 100%;
  padding: 0 16px;
}

.breadcrumb {
  font-size: 18px;
  font-weight: 500;
}

.right {
  display: flex;
  align-items: center;
  gap: 8px;
}

.action-btn {
  width: 40px;
  height: 40px;
  padding: 8px;
  border-radius: 8px;
  transition: var(--hover-transition);
  color: var(--el-text-color-regular);
  background-color: transparent;
  border: 1px solid transparent;
}

.action-btn:hover {
  color: var(--primary-color);
  background-color: var(--el-bg-color-overlay);
  border-color: var(--el-border-color-light);
}

.action-btn.is-active {
  color: var(--primary-color);
  background-color: var(--el-bg-color-overlay);
  border-color: var(--primary-color);
}

.action-btn :deep(.el-icon) {
  font-size: 20px;
  width: 20px;
  height: 20px;
}

@media (max-width: 768px) {
  .action-btn {
    width: 36px;
    height: 36px;
    padding: 6px;
  }

  .action-btn :deep(.el-icon) {
    font-size: 18px;
    width: 18px;
    height: 18px;
  }
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 8px;
}
</style>
