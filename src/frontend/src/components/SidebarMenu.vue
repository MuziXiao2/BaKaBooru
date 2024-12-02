<template>
  <el-menu
      :default-active="currentGalleryId.toString()"
      class="el-menu-vertical-demo"
  >
    <el-menu-item
        v-for="gallery in galleries"
        :key="gallery.id"
        :index="gallery.id.toString()"
        @click="selectGallery(gallery.id)"
    >
      <span>{{ gallery.name }}</span>
    </el-menu-item>
  </el-menu>
</template>

<script setup>
import {defineProps, defineEmits} from 'vue';

defineProps({
  galleries: Array,
  currentGalleryId: Number,
});
const emit = defineEmits(['selectGallery']);

const selectGallery = (id) => {
  emit('selectGallery', id);
};
</script>

