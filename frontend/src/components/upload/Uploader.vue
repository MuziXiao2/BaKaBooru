<script setup lang="ts">
import { useUploadStateStore } from '@/stores/modules/upload/upload-state.ts'
import { useSoucreStore } from '@/stores'
import { onMounted } from 'vue'

const sourceStore = useSoucreStore()
const uploadStateStore = useUploadStateStore()

const handleFinish = ({ event }: { event?: ProgressEvent }) => {
  const response = JSON.parse((event?.target as XMLHttpRequest).response)
  uploadStateStore.addData(response.title, response.uuid, response.extension, response.size)
}

onMounted(async () => {
  await sourceStore.fetchGroupsAndSources()
  uploadStateStore.updateGroupSelectOptions()
})

const handleGroupSelect = () => {
  uploadStateStore.updateSourceSelectOptions()
}

const handleSourceSelect = () => {}
</script>

<template>
  <n-h1>上传图片</n-h1>
  <n-flex :wrap="false" class="select-container">
    <n-select
      v-model:value="uploadStateStore.currentGroupId"
      @update:value="handleGroupSelect"
      placeholder="请选择组"
      :options="uploadStateStore.groupSelectOptions"
      class="short-select"
    />
    <n-select
      v-model:value="uploadStateStore.currentSourceId"
      @update:value="handleSourceSelect"
      placeholder="请选择图源"
      :options="uploadStateStore.sourceSelectOptions"
      class="short-select"
    />
    <n-button @click="uploadStateStore.saveData">保存</n-button>
  </n-flex>
  <n-upload
    :show-file-list="false"
    @finish="handleFinish"
    multiple
    directory-dnd
    action="http://localhost:8080/api?type=upload"
  >
    <n-upload-dragger>
      <n-text style="font-size: 16px"> 点击或者拖动文件到该区域来上传</n-text>
    </n-upload-dragger>
  </n-upload>
</template>

<style scoped>
.n-upload {
  width: auto;
  height: auto;
}

.select-container {
  gap: 16px;
}

.short-select {
  width: 200px;
}
</style>
