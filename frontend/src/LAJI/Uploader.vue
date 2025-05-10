<script setup lang="ts">
import { useUploadStateStore } from '@/stores/upload/upload-state.ts'
import { useSoucreStore } from '@/stores'
import { computed, onMounted } from 'vue'
import SourceSelect from '@/components/common/select/SourceSelect.vue'
import type { Source } from '@/types'

const sourceStore = useSoucreStore()
const uploadStateStore = useUploadStateStore()

onMounted(async () => {
  await sourceStore.update()
})

const handleSelect = (source: Source) => {
  uploadStateStore.currentSource = source
}

const handleFinish = ({ event }: { event?: ProgressEvent }) => {
  const response = JSON.parse((event?.target as XMLHttpRequest).response).data
  uploadStateStore.addData(response)
}

const baseUrl = 'http://localhost:8080/image'
const uploadUrl = computed(() => {
  const source = uploadStateStore.currentSource
  return source ? baseUrl + `/image/${source.id}/upload` : undefined
})
</script>

<template>
  <n-h1>上传图片</n-h1>
  <n-flex :wrap="false" class="select-container">
    <source-select :sources="sourceStore.sources" :on-selected="handleSelect" />
    <n-button @click="uploadStateStore.saveData">保存</n-button>
  </n-flex>
  <n-upload
    :show-file-list="false"
    @finish="handleFinish"
    multiple
    directory-dnd
    :action="uploadUrl"
  >
    <n-upload-dragger>
      <n-text style="font-size: 16px">点击或者拖动文件到该区域来上传</n-text>
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
