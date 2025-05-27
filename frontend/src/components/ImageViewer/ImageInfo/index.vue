<template>
  <ImageInfoLayout>
    <HeaderInfo />
    <FileInfo />
    <FileList />
    <InfoCard title="标签">
      <div class="tags-container"></div>
    </InfoCard>

    <template #actions>
      <el-button @click="handleClose">关闭</el-button>
    </template>
  </ImageInfoLayout>
</template>

<script setup lang="ts">
import { useImageViewerStore } from '@/stores/useImageViewerStore.ts'
import ImageInfoLayout from './ImageInfoLayout.vue'
import InfoCard from './InfoCard.vue'
import HeaderInfo from './HeaderInfo.vue'
import FileInfo from './FileInfo.vue'
import FileList from './FileList.vue'

defineOptions({
  name: 'ImageInfoPanel',
})

const imageViewerStore = useImageViewerStore()

const handleClose = () => {
  imageViewerStore.closeImageViewer()
}
</script>

<style scoped>
.tags-container {
  min-height: 32px;
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}
</style>
