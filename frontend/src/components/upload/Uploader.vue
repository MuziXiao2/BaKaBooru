<script setup lang="ts">
import { useUploadStateStore } from '@/stores/modules/upload/upload-state.ts'

const uploadStateStore = useUploadStateStore()

const handleFinish = ({ event }: { event?: ProgressEvent }) => {
  const response = JSON.parse((event?.target as XMLHttpRequest).response)
  uploadStateStore.addData(response.title, response.uuid, response.extension, response.size)
}
</script>

<template>
  <n-h1>上传图片</n-h1>
  选择图源:
  <n-select />
  <n-upload
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
  <n-button @click="uploadStateStore.saveData">保存</n-button>
</template>

<style scoped>
.n-upload {
  width: auto;
  height: auto;
}
</style>
