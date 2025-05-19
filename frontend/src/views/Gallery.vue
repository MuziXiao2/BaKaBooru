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
        :images="store.images"
        :loading="store.loading"
        :column-width="300"
        :gap="10"
        :ssr-columns="1"
        :min-columns="2"
        :max-columns="5"
      />
      <!-- 分页控件 -->
      <div class="pagination-wrapper">
        <el-select
          v-model="store.pageSize"
          @change="handlePageSizeChange"
          class="page-size-select"
          placeholder="每页数量"
          aria-label="选择每页图片数量"
        >
          <el-option label="10 每页" :value="10" />
          <el-option label="20 每页" :value="20" />
          <el-option label="50 每页" :value="50" />
        </el-select>

        <div class="pagination-center">
          <el-button
            :disabled="store.page === 1 || store.loading"
            @click="handlePageChange(store.page - 1)"
            type="primary"
            aria-label="上一页"
          >
            上一页
          </el-button>
          <span class="page-info">第 {{ store.page }} 页</span>
          <el-button
            :disabled="store.noMoreData || store.loading"
            @click="handlePageChange(store.page + 1)"
            type="primary"
            aria-label="下一页"
          >
            下一页
          </el-button>
        </div>
      </div>
    </template>
  </GalleryLayout>
</template>

<script setup lang="ts">
import { onMounted } from 'vue'
import { debounce } from 'lodash-es'
import Masonry from '@/components/Masonry.vue'
import Filter from '@/components/Filter.vue'
import GalleryLayout from '@/views/layouts/GalleryLayout.vue'
import AdvancedSearchBar from '@/components/AdvancedSearchBar.vue'
import { useImageSearchStore } from '@/stores/useImageSearchStore'

const store = useImageSearchStore()

// 处理分页变化，包含防抖
const handlePageChange = debounce((targetPage: number) => {
  store.goToPage(targetPage)
  store.fetchImages()
}, 300)

// 处理每页数量变化，包含防抖
const handlePageSizeChange = debounce(() => {
  store.setPageSize(store.pageSize)
  store.fetchImages()
}, 300)

onMounted(() => {
  store.goToPage(1) // 设置初始页码
  store.fetchImages() // 触发查询
})
</script>

<style scoped>
.pagination-wrapper {
  display: flex;
  align-items: center;
  padding: 12px 20px; /* 上下内边距12px，左右20px */
  border-top: 1px solid #ebeef5;
  background-color: #fff;
  position: sticky;
  bottom: 0;
  z-index: 10;
  gap: 20px;
}

/* 每页数量选择框靠左 */
.page-size-select {
  width: 120px;
}

/* 中间部分弹性占满剩余空间，内容居中 */
.pagination-center {
  flex: 1; /* 占满剩余宽度 */
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 16px;
}

.page-info {
  font-size: 16px;
  color: #333;
}
</style>
