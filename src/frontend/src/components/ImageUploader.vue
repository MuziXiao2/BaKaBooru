<template>
  <div class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
    <div class="bg-white p-6 rounded-lg w-full max-w-md">
      <h2 class="text-2xl font-bold mb-4">Upload Image</h2>
      <form @submit.prevent="handleSubmit" class="space-y-4">
        <div>
          <label for="image" class="block text-sm font-medium text-gray-700">Select Image</label>
          <input
              id="image"
              type="file"
              accept="image/*"
              @change="handleFileChange"
              class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm"
          />
        </div>
        <button
            type="submit"
            :disabled="!file || isUploading"
            class="w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded disabled:opacity-50"
        >
          {{ isUploading ? 'Uploading...' : 'Upload' }}
        </button>
      </form>
      <button @click="$emit('close')" class="mt-4 text-sm text-gray-600 hover:text-gray-800">Cancel</button>
    </div>
  </div>
</template>

<script setup>
import {ref, defineEmits} from 'vue';

const emit = defineEmits(['close']);

const file = ref(null);
const isUploading = ref(false);

const handleFileChange = (event) => {
  if (event.target.files && event.target.files[0]) {
    file.value = event.target.files[0];
  }
};

const handleSubmit = async () => {
  if (!file.value) return;

  isUploading.value = true;
  const formData = new FormData();
  formData.append('image', file.value);

  try {
    const response = await fetch('http://localhost:8080/gallery/0/upload', {
      method: 'POST',
      body: formData,
    });

    if (response.status === 201) {
      alert('Image uploaded successfully!');
      emit('close');
    } else {
      throw new Error('Failed to upload image');
    }
  } catch (error) {
    console.error('Error uploading image:', error);
    alert('Failed to upload image. Please try again.');
  } finally {
    isUploading.value = false;
  }
};
</script>