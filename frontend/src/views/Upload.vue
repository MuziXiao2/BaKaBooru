<script setup lang="ts">
import UploadLayout from '@/components/layout/UploadLayout.vue'
import { ArchiveOutline as ArchiveIcon } from '@vicons/ionicons5'
import { addImage } from '@/api'

const handleFinish = ({ event }: { event?: ProgressEvent }) => {
  const response = JSON.parse((event?.target as XMLHttpRequest).response)
  addImage('test', response.url, 1)
}
</script>

<template>
  <upload-layout>
    <n-upload
      multiple
      directory-dnd
      action="http://localhost:8080/api/images/upload"
      :max="5"
      @finish="handleFinish"
    >
      <n-upload-dragger>
        <div style="margin-bottom: 12px">
          <n-icon size="48" :depth="3">
            <ArchiveIcon />
          </n-icon>
        </div>
        <n-text style="font-size: 16px"> 点击或者拖动文件到该区域来上传</n-text>
        <n-p depth="3" style="margin: 8px 0 0 0">
          请不要上传敏感数据，比如你的银行卡号和密码，信用卡号有效期和安全码
        </n-p>
      </n-upload-dragger>
    </n-upload>
  </upload-layout>
</template>

<style scoped></style>
