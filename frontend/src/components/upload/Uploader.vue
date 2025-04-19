<script setup lang="ts">
import { useUploadStateStore } from '@/stores/modules/upload/upload-state.ts'
import { useSoucreStore } from '@/stores'
import { onMounted } from 'vue'
import SourceSelect from '@/components/common/select/SourceSelect.vue'
import type { Source } from '@/types'

const sourceStore = useSoucreStore()
const uploadStateStore = useUploadStateStore()

const handleFinish = ({ event }: { event?: ProgressEvent }) => {
  const response = JSON.parse((event?.target as XMLHttpRequest).response)
  uploadStateStore.addData(response.title, response.uuid, response.extension, response.size)
}

onMounted(async () => {
  await sourceStore.update()
})

const handleSelect = (source: Source) => {
  console.log(source)
}
</script>

<template>
  <n-h1>上传图片</n-h1>
  <n-flex :wrap="false" class="select-container">
    <source-select
      :groups="sourceStore.groups"
      :sources="sourceStore.sources"
      :on-selected="handleSelect"
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
