<script setup lang="ts">
import { addImage } from '@/api/image.ts'
import { ref } from 'vue'

const fileList = ref([])

const handleFinish = ({ event }: { event?: ProgressEvent }) => {
  const response = JSON.parse((event?.target as XMLHttpRequest).response)
  addImage({
    title: 'test',
    url: response.url,
    size: response.size,
    atlas_id: 1,
  })
}
</script>

<template>
  <n-h1>上传图片</n-h1>
  选择图源:
  <n-select />
  <n-upload
    v-model:file-list="fileList"
    :show-file-list="false"
    @finish="handleFinish"
    multiple
    directory-dnd
    action="http://localhost:8080/api/images/upload"
  >
    <n-upload-dragger>
      <n-text style="font-size: 16px"> 点击或者拖动文件到该区域来上传</n-text>
      <n-flex justify="center" />
    </n-upload-dragger>
  </n-upload>
</template>

<style scoped>
.n-upload {
  width: auto;
  height: auto;
}
</style>
