<template>
  <div class="space-y-8">
    <div class="flex justify-end">
      <label
          for="file-upload"
          class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded inline-flex items-center cursor-pointer"
      >
        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4" />
        </svg>
        上传图片
      </label>
      <input
          id="file-upload"
          type="file"
          multiple
          accept="image/*"
          class="hidden"
          @change="handleFileUpload"
      >
    </div>
    <ImageGrid :images="images" />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import ImageGrid from './ImageGrid.vue';

const images = ref([]);

const fetchImages = async () => {
  try {
    const response = await fetch('http://localhost:8080/gallery/0/images');
    if (!response.ok) {
      throw new Error('Failed to fetch images');
    }
    images.value = await response.json();
  } catch (error) {
    console.error('Error fetching images:', error);
  }
};

const handleFileUpload = async (event) => {
  const files = event.target.files;
  if (files.length === 0) return;

  const formData = new FormData();
  for (let i = 0; i < files.length; i++) {
    formData.append('images', files[i]);
  }

  try {
    const response = await fetch('http://localhost:8080/gallery/0/upload', {
      method: 'POST',
      body: formData,
    });

    if (response.status === 201) {
      alert('图片上传成功!');
      await fetchImages(); // 重新获取所有图片
    } else {
      throw new Error('Failed to upload images');
    }
  } catch (error) {
    console.error('Error uploading images:', error);
    alert('图片上传失败,请重试。');
  }
};

onMounted(() => {
  fetchImages();
});
</script>