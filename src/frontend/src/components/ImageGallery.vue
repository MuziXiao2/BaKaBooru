<template>
  <el-container>
    <el-aside width="250px" v-show="isSidebarOpen">
      <SidebarMenu
          :galleries="galleries"
          :currentGalleryId="currentGalleryId"
          @selectGallery="selectGallery"
      />
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
          <h1 class="text-2xl font-bold">{{ currentGalleryName }}</h1>
        </el-col>
        <el-col :span="12" class="text-right">
          <el-upload
              action="http://localhost:8080/images"
              :data="{ galleryId: currentGalleryId }"
              :on-success="handleUploadSuccess"
              :on-error="handleUploadError"
              multiple
              :show-file-list="false"
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
import {ref, computed, onMounted} from 'vue';
import {ElMessage} from 'element-plus';
import ImageGrid from './ImageGrid.vue';
import SidebarMenu from './SidebarMenu.vue';

const images = ref([]);
const currentGalleryId = ref(0);
const isSidebarOpen = ref(true);
const galleries = ref([
  {id: 0, name: '默认图库'},
  {id: 1, name: '图库 1'},
  {id: 2, name: '图库 2'},
]);

const currentGalleryName = computed(() => {
  const gallery = galleries.value.find(g => g.id === currentGalleryId.value);
  return gallery ? gallery.name : '';
});

const toggleSidebar = () => {
  isSidebarOpen.value = !isSidebarOpen.value;
};

const selectGallery = (id) => {
  currentGalleryId.value = id;
  fetchImages();
};

const fetchImages = async () => {
  try {
    const response = await fetch(`http://localhost:8080/gallery/${currentGalleryId.value}`);
    if (!response.ok) {
      throw new Error('Failed to fetch images');
    }
    images.value = await response.json();
  } catch (error) {
    console.error('Error fetching images:', error);
    ElMessage.error('获取图片失败,请重试。');
  }
};

const handleUploadSuccess = () => {
  ElMessage.success('图片上传成功!');
  fetchImages();
};

const handleUploadError = (error) => {
  console.error('Error uploading image:', error);
  ElMessage.error('图片上传失败,请重试。');
};

const handleDeleteImages = async (imageIds) => {
  try {
    const response = await fetch(`http://localhost:8080/images`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ids: imageIds}),
    });

    if (response.ok) {
      ElMessage.success('图片删除成功!');
      await fetchImages();
    } else {
      throw new Error('Failed to delete images');
    }
  } catch (error) {
    console.error('Error deleting images:', error);
    ElMessage.error('图片删除失败,请重试。');
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

