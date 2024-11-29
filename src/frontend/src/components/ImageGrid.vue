<template>
  <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
    <div v-for="image in images" :key="image.id" class="bg-white rounded-lg overflow-hidden shadow-md">
      <img :src="image.url" :alt="image.title" class="w-full h-48 object-cover" />
      <div class="p-4">
        <p class="text-gray-700 text-base">{{ image.title }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import {defineProps} from 'vue';

defineProps({
  images: {
    type: Array,
    required: true
  }
});
</script>

