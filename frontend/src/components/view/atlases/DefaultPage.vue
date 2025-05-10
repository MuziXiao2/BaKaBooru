<template>
  <vue-masonry :cols="4" :gutter="20">
    <div class="masonry-item" v-for="(item, index) in images" :key="index">
      <n-card hoverable>
        <template #cover>
          <n-image :src="item.url" width="100%" height="auto" />
        </template>
        <template #footer>
          <div>{{ item.title }} - {{ item.author }}</div>
        </template>
      </n-card>
    </div>
  </vue-masonry>
</template>

<script setup>
import { ref } from 'vue'
import { NImage, NCard } from 'naive-ui'

const urls =
  'http://192.168.31.126:9000/bakabooru/44579fdae9f04823b27a24ade3e4d1ba801cc8b40c7e33180c00fd49f114e240'

const images = ref([
  { id: 1, url: urls, title: '插画 1', author: '画师 A' },
  { id: 2, url: urls, title: '插画 2', author: '画师 B' },
  { id: 3, url: urls, title: '插画 3', author: '画师 C' },
  { id: 4, url: urls, title: '插画 4', author: '画师 D' },
  { id: 5, url: urls, title: '插画 5', author: '画师 E' },
])
</script>

<style scoped>
.masonry-item {
  display: flex;
  flex-direction: column;
}

.masonry-item img {
  max-width: 300px;  /* 设置最大宽度 */
  width: 100%;       /* 保证图片宽度自适应容器 */
  height: auto;      /* 保持图片比例 */
}
</style>
