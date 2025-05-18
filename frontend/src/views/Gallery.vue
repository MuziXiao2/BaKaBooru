<template>
  <ImageMasonry
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

<script setup lang="ts">
import { ref } from 'vue'
import { ElButton } from 'element-plus'
import ImageMasonry from '@/components/ImageMasonry.vue'

// 图片数据接口
interface ImageItem {
  id: number
  url: string
  alt: string
}

// 分页状态
const images = ref<ImageItem[]>([])
const page = ref(1)
const pageSize = 50 // 每页固定 10 张图片
const loading = ref(false)
const noMoreData = ref(false)

// 加载指定页的图片
const fetchImages = async (pageNum: number) => {
  try {
    loading.value = true
    // 模拟后端 API，实际应替换为真实请求
    const response = await new Promise<ImageItem[]>((resolve) => {
      setTimeout(() => {
        const newImages: ImageItem[] = Array.from({ length: pageSize }, (_, i) => ({
          id: (pageNum - 1) * pageSize + i,
          url: `https://picsum.photos/300/${200 + (i % 50)}?random=${(pageNum - 1) * pageSize + i}`,
          alt: `Image ${(pageNum - 1) * pageSize + i + 1}`,
        }))
        resolve(newImages)
      }, 1000)
    })

    // 模拟无更多数据的场景（例如第 5 页后无数据）
    if (pageNum >= 5) {
      noMoreData.value = true
      images.value = []
      return
    }

    // 更新图片数据
    images.value = response
    noMoreData.value = response.length < pageSize
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
