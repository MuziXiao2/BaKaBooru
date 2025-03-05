<script setup lang="ts">
import UploadLayout from '@/components/layout/UploadLayout.vue'
import { addImage } from '@/api/image.ts'

import { ref } from 'vue'

const data = ref([])
const columns = [
  {
    title: 'Name',
    key: 'name',
  },
  {
    title: 'Age',
    key: 'age',
  },
  {
    title: 'Address',
    key: 'address',
  },
  {
    title: 'Tags',
    key: 'tags',
  },
  {
    title: 'Action',
    key: 'actions',
  },
]

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
  <upload-layout>
    <template #header>
      <n-page-header title="上传图片">
        <template #extra>
          <n-flex justify="end">
            <n-button>选择图源</n-button>
            <n-upload
              multiple
              directory-dnd
              action="http://localhost:8080/api/images/upload"
              :max="5"
              @finish="handleFinish"
            >
              <n-button>上传</n-button>
            </n-upload>
            <n-button>扫描目录</n-button>
          </n-flex>
        </template>
      </n-page-header>
    </template>
    <template #content>
      <n-data-table :columns="columns" :data="data" :bordered="false" />
    </template>
    <template #sider>
      <n-h1>Image Info</n-h1>
    </template>
  </upload-layout>
</template>

<style scoped>
.n-upload {
  width: auto;
  height: auto;
}
</style>
