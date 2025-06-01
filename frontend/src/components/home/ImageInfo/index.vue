<template>
  <ImageInfoLayout>
    <HeaderInfo />
    <TagList/>
    <FileList />
    <FileInfo />
    <template #actions>
      <el-button @click="handleClose">关闭</el-button>
    </template>
  </ImageInfoLayout>
</template>

<script setup lang="ts">
import { useImageViewerStore } from '@/stores/useImageViewerStore.ts'
import ImageInfoLayout from './ImageInfoLayout.vue'
import HeaderInfo from './HeaderInfo.vue'
import FileInfo from './FileInfo.vue'
import FileList from './FileList.vue'
import TagList from '@/components/home/ImageInfo/TagList.vue'

defineOptions({
  name: 'ImageInfoPanel',
})

const imageViewerStore = useImageViewerStore()

const handleClose = () => {
  imageViewerStore.closeImageViewer()
}
</script>

<style scoped>
</style>
