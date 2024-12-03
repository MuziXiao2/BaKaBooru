<template>
  <el-container>
    <el-aside width="250px" v-show="isSidebarOpen">
      <SidebarMenu/>
    </el-aside>
    <el-main>
      <el-button
          @click="toggleSidebar"
          type="primary"
          icon="el-icon-menu"
          circle
          class="sidebar-toggle"
      ></el-button>
      <el-row :gutter="20" class="mb-4">
        <el-col :span="12">
          <h1 class="text-2xl font-bold">Default Gallery</h1>
        </el-col>
        <el-col :span="12" class="text-right">
          <el-upload
              :action="`${apiBaseUrl}/image`"
              :on-success="handleUploadSuccess"
              :on-error="handleUploadError"
              :before-upload="beforeUpload"
              multiple
              :show-file-list="false"
              name="image_file"
          >
            <el-button type="primary" icon="el-icon-upload">上传图片</el-button>
          </el-upload>
        </el-col>
      </el-row>
      <ImageGrid :images="images" @deleteImages="handleDeleteImages"/>
    </el-main>
  </el-container>
</template>

<script setup>
/* eslint-disable */
import {ref, onMounted} from 'vue';
import {ElMessage} from 'element-plus';
import ImageGrid from './ImageGrid.vue';
import SidebarMenu from './SidebarMenu.vue';

const apiBaseUrl = 'http://localhost:8080';
const images = ref([]);
const isSidebarOpen = ref(true);

const toggleSidebar = () => {
  isSidebarOpen.value = !isSidebarOpen.value;
};

const fetchImages = async () => {
  try {
    const response = await fetch(`${apiBaseUrl}/gallery/default`);
    if (!response.ok) {
      throw new Error('Failed to fetch images');
    }
    images.value = await response.json();
  } catch (error) {
    console.error('Error fetching images:', error);
    ElMessage.error('获取图片失败，请重试。');
  }
};

const beforeUpload = (file) => {
  // You can add file type and size validation here if needed
  return true;
};

const handleUploadSuccess = async (response, file) => {
  try {
    const imageUrl = response; // The API returns the URL directly
    await addImageToGallery(file.name, imageUrl);
    ElMessage.success(`图片 ${file.name} 上传成功!`);
    await fetchImages();
  } catch (error) {
    console.error('Error adding image to gallery:', error);
    ElMessage.error(`图片 ${file.name} 添加到图库失败，请重试。`);
  }
};

const addImageToGallery = async (name, url) => {
  const formData = new FormData();
  formData.append('name', name);
  formData.append('url', url);

  const response = await fetch(`${apiBaseUrl}/gallery/default`, {
    method: 'POST',
    body: formData
  });

  if (!response.ok) {
    throw new Error('Failed to add image to gallery');
  }
};

const handleUploadError = (error, file) => {
  console.error('Error uploading image:', error);
  ElMessage.error(`图片 ${file.name} 上传失败，请重试。`);
};

const handleDeleteImages = async (imageIds) => {
  try {
    const queryString = imageIds.map(id => `ids=${id}`).join('&');
    const response = await fetch(`${apiBaseUrl}/gallery/default?${queryString}`, {
      method: 'DELETE',
    });

    if (response.status === 204) {
      ElMessage.success('图片删除成功!');
      await fetchImages();
    } else {
      throw new Error('Failed to delete images');
    }
  } catch (error) {
    console.error('Error deleting images:', error);
    ElMessage.error('图片删除失败，请重试。');
  }
};

onMounted(() => {
  fetchImages();
});
</script>

<style scoped>
.sidebar-toggle {
  position: fixed;
  top: 20px;
  left: 20px;
  z-index: 1000;
}
</style>

