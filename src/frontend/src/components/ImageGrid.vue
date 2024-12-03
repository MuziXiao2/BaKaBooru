<template>
  <div>
    <el-row :gutter="20" class="mb-4">
      <el-col :span="24">
        <el-button type="danger" @click="deleteSelected" :disabled="selectedImages.length === 0">
          删除选中图片 ({{ selectedImages.length }})
        </el-button>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :xs="24" :sm="12" :md="8" :lg="6" v-for="image in images" :key="image.id" class="mb-4">
        <el-card :body-style="{ padding: '0px' }">
          <el-checkbox v-model="image.selected" @change="updateSelected(image)" class="image-checkbox"></el-checkbox>
          <img :src="image.url" :alt="image.name" class="image">
          <div style="padding: 14px;">
            <span>{{ image.name }}</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
/* eslint-disable */
import { ref, defineProps, defineEmits, watch } from 'vue';

const props = defineProps({
  images: {
    type: Array,
    required: true
  }
});

const emit = defineEmits(['deleteImages']);

const selectedImages = ref([]);

const updateSelected = (image) => {
  if (image.selected) {
    selectedImages.value.push(image.id);
  } else {
    selectedImages.value = selectedImages.value.filter(id => id !== image.id);
  }
};

const deleteSelected = () => {
  emit('deleteImages', selectedImages.value);
  selectedImages.value = [];
};

watch(() => props.images, (newImages) => {
  newImages.forEach(image => {
    if (!image.hasOwnProperty('selected')) {
      image.selected = false;
    }
  });
}, { deep: true, immediate: true });
</script>

<style scoped>
.image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}
.image-checkbox {
  position: absolute;
  top: 10px;
  left: 10px;
  z-index: 1;
}
</style>

