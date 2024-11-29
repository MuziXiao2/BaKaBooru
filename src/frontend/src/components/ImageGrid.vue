<template>
  <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
    <template v-if="isLoading">
      <div v-for="index in 8" :key="index" class="bg-gray-200 rounded-lg overflow-hidden">
        <div class="h-48 w-full animate-pulse bg-gray-300"></div>
      </div>
    </template>
    <template v-else>
      <div v-for="image in images" :key="image.id" class="bg-white rounded-lg overflow-hidden shadow-md">
        <img :src="image.url" :alt="image.title" class="w-full h-48 object-cover"/>
      </div>
    </template>
  </div>
</template>

<script setup>
import {ref, onMounted} from 'vue';

const images = ref([]);
const isLoading = ref(true);

const fetchImages = async () => {
  try {
    const response = await fetch('http://localhost:8080/gallery/0/images');
    if (!response.ok) {
      throw new Error('Failed to fetch images');
    }
    images.value = await response.json();
  } catch (error) {
    console.error('Error fetching images:', error);
  } finally {
    isLoading.value = false;
  }
};

onMounted(() => {
  fetchImages();
});
</script>