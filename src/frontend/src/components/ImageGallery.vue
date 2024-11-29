<template>
  <div class="space-y-8">
    <div class="flex justify-end">
      <button
          @click="isUploaderOpen = true"
          class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded inline-flex items-center"
      >
        <PlusCircle class="mr-2 h-4 w-4" />
        Upload Image
      </button>
    </div>
    <ImageGrid />
    <ImageUploader v-if="isUploaderOpen" @close="isUploaderOpen = false" />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { PlusCircle } from 'lucide-vue-next';
import ImageGrid from './ImageGrid.vue';
import ImageUploader from './ImageUploader.vue';

const isUploaderOpen = ref(false);
</script>