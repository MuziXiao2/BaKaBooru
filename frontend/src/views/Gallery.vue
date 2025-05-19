<template>
  <GalleryLayout>
    <template #side>
      <Filter />
    </template>
    <template #header>
      <AdvancedSearchBar />
    </template>
    <template #main>
      <Masonry
        :images="images"
        :loading="loading"
        :column-width="300"
        :gap="8"
        :ssr-columns="1"
        :min-columns="2"
        :max-columns="5"
      />
      <div class="pagination">
        <el-button :disabled="page === 1 || loading" @click="goToPage(page - 1)" type="primary">
          上一页
        </el-button>
        <span class="page-info">第 {{ page }} 页</span>
        <el-button :disabled="noMoreData || loading" @click="goToPage(page + 1)" type="primary">
          下一页
        </el-button>
      </div>
    </template>
  </GalleryLayout>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElButton } from 'element-plus'
import { getImageFileUrl, queryImages } from '@/api'

import Masonry from '@/components/Masonry.vue'
import Filter from '@/components/Filter.vue'
import GalleryLayout from '@/views/layouts/GalleryLayout.vue'
import AdvancedSearchBar from '@/components/AdvancedSearchBar.vue'

// 图片数据接口
interface ImageItem {
  uuid: string
  title: string
  url: string
}

// 分页状态
const images = ref<ImageItem[]>([])
const page = ref(1)
const pageSize = 10
const loading = ref(false)
const noMoreData = ref(false)

// 加载指定页的图片
const fetchImages = async (pageNum: number) => {
  try {
    loading.value = true

    // 模拟无更多数据的场景
    if (pageNum >= 3) {
      noMoreData.value = true
      images.value = []
      return
    }

    // 更新图片数据
    const imageQueryPage = await queryImages({
      page: page.value,
      size: pageSize,
    })

    const promises = imageQueryPage.content.map(async (imageQuery) => {
      const url = await getImageFileUrl(imageQuery.coverHash)
      return {
        uuid: imageQuery.uuid,
        title: imageQuery.title,
        url: url,
      }
    })

    // 等待所有异步完成
    images.value = await Promise.all(promises)

    noMoreData.value = imageQueryPage.content.length < pageSize
  } catch (error) {
    console.error('加载图片失败:', error)
    images.value = []
  } finally {
    loading.value = false
  }
}

// 切换到指定页
const goToPage = (newPage: number) => {
  if (newPage < 1 || loading.value) return
  page.value = newPage
  fetchImages(page.value)
}

// 初始化加载第一页
fetchImages(page.value)
</script>

<style scoped>
/* 分页控件 */
.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 16px;
  padding: 20px 0;
  position: sticky;
  bottom: 0;
  background-color: #fff;
  z-index: 10;
  border-top: 1px solid #ebeef5;
}

.page-info {
  font-size: 16px;
  color: #333;
}
</style>
