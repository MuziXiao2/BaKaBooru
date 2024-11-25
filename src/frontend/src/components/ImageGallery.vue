<template>
  <div class="container mx-auto px-4">
    <h1 class="text-3xl font-bold text-center my-8">Image Gallery</h1>

    <!-- 图片上传部分 -->
    <div class="mb-8">
      <input
          type="file"
          @change="handleFileUpload"
          accept="image/*"
          class="hidden"
          ref="fileInput"
      >
      <button
          @click="$refs.fileInput.click()"
          class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
      >
        Upload Image
      </button>
      <p v-if="uploadStatus" class="mt-2" :class="{'text-green-500': uploadStatus === 'success', 'text-red-500': uploadStatus === 'error'}">
        {{ uploadStatusMessage }}
      </p>
    </div>

    <div v-if="loading" class="text-center">
      <p class="text-xl">Loading images...</p>
    </div>
    <div v-else-if="error" class="text-center text-red-500">
      <p class="text-xl">{{ error }}</p>
    </div>
    <div v-else class="image-grid">
      <div v-for="image in images" :key="image.id" class="image-item">
        <img
            :src="image.url"
            :alt="image.title"
            class="w-full h-full object-cover rounded-lg shadow-md transition-transform duration-300 hover:scale-105"
            loading="lazy"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const images = ref([]);
const loading = ref(true);
const error = ref(null);
const fileInput = ref(null);
const uploadStatus = ref('');
const uploadStatusMessage = ref('');

const fetchImages = async () => {
  try {
    const response = await fetch('http://localhost:8080/gallery/0/images');
    if (!response.ok) {
      throw new Error('Failed to fetch images');
    }
    const data = await response.json();
    images.value = data;
  } catch (err) {
    error.value = 'An error occurred while fetching images. Please try again later.';
  } finally {
    loading.value = false;
  }
};

const handleFileUpload = async (event) => {
  const file = event.target.files[0];
  if (!file) return;

  const formData = new FormData();
  formData.append('image', file);

  try {
    uploadStatus.value = 'uploading';
    uploadStatusMessage.value = 'Uploading image...';

    const response = await fetch('http://localhost:8080/upload', {
      method: 'POST',
      body: formData
    });

    if (response.status === 201) {
      uploadStatus.value = 'success';
      uploadStatusMessage.value = 'Image uploaded successfully!';
      // 重新获取图片列表以显示新上传的图片
      await fetchImages();
    } else {
      throw new Error('Failed to upload image');
    }
  } catch (err) {
    uploadStatus.value = 'error';
    uploadStatusMessage.value = 'Failed to upload image. Please try again.';
  }
};

onMounted(() => {
  fetchImages();
});
</script>

<style scoped>
.image-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 1rem;
}

.image-item {
  aspect-ratio: 1 / 1;
  overflow: hidden;
}

@media (max-width: 640px) {
  .image-grid {
    grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
  }
}
</style>