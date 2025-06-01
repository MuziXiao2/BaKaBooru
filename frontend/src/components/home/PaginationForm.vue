<template>
  <div class="pagination-wrapper">
    <el-select
      v-model="currentPageSize"
      @change="handlePageSizeChange"
      class="page-size-select"
      placeholder="每页数量"
      aria-label="选择每页图片数量"
      filterable
      allow-create
    >
      <el-option label="5 每页" :value="5" />
      <el-option label="20 每页" :value="20" />
      <el-option label="50 每页" :value="50" />
      <el-option label="100 每页" :value="100" />
    </el-select>

    <div class="pagination-center">
      <el-button
        :disabled="currentPage === 1 || loading"
        @click="handlePageChange(currentPage - 1)"
        type="primary"
        aria-label="上一页"
      >
        上一页
      </el-button>
      <span class="page-info">第 {{ currentPage }} 页</span>
      <el-button
        :disabled="noMoreData || loading"
        @click="handlePageChange(currentPage + 1)"
        type="primary"
        aria-label="下一页"
      >
        下一页
      </el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { debounce } from 'lodash-es'
import { useImageStore } from '@/stores/useImageStore.ts'
import { storeToRefs } from 'pinia'

const imageStore = useImageStore()

const { loading, currentPage, currentPageSize, noMoreData } = storeToRefs(imageStore)

const handlePageChange = debounce((targetPage: number) => {
  imageStore.setPage(targetPage)
  imageStore.queryImages()
}, 100)

const handlePageSizeChange = debounce(() => {
  imageStore.setPageSize(currentPageSize.value)
  imageStore.queryImages()
}, 100)
</script>

<style scoped>
.pagination-wrapper {
  display: flex;
  align-items: center;
  background-color: var(--el-bg-color);
  position: sticky;
  bottom: 0;
  z-index: 10;
  gap: 20px;
  height: 100%;
  width: 100%;

}

.page-size-select {
  width: 10%;
  min-width: 120px;
}

.pagination-center {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 16px;
}

.page-info {
  font-size: 16px;
  color: var(--el-text-color-primary);
}
</style>
