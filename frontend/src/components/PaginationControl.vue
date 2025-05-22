```vue
<template>
  <div class="pagination-wrapper">
    <el-select
      v-model="paginationStore.pageSize"
      @change="handlePageSizeChange"
      class="page-size-select"
      placeholder="每页数量"
      filterable
      allow-create
      aria-label="选择每页图片数量"
    >
      <el-option label="20 每页" :value="20" />
      <el-option label="50 每页" :value="50" />
      <el-option label="100 每页" :value="100" />
    </el-select>

    <div class="pagination-center">
      <el-button
        :disabled="paginationStore.page === 1 || imageStore.loading"
        @click="handlePageChange(paginationStore.page - 1)"
        type="primary"
        aria-label="上一页"
      >
        上一页
      </el-button>
      <span class="page-info">第 {{ paginationStore.page }} 页</span>
      <el-button
        :disabled="paginationStore.noMoreData || imageStore.loading"
        @click="handlePageChange(paginationStore.page + 1)"
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
import { useImageStore } from '@/stores/useImageStore'
import { usePaginationStore } from '@/stores/usePaginationStore'
import { storeToRefs } from 'pinia'

const imageStore = useImageStore()
const paginationStore = usePaginationStore()
const { pageSize } = storeToRefs(paginationStore)

const handlePageChange = debounce((targetPage: number) => {
  paginationStore.goToPage(targetPage)
  imageStore.queryImages()
}, 300)

const handlePageSizeChange = debounce(() => {
  paginationStore.setPageSize(pageSize.value)
  imageStore.queryImages()
}, 300)
</script>

<style scoped>
.pagination-wrapper {
  display: flex;
  align-items: center;
  padding: 12px 20px;
  border-top: 1px solid #ebeef5;
  background-color: #fff;
  position: sticky;
  bottom: 0;
  z-index: 10;
  gap: 20px;
}

.page-size-select {
  width: 120px;
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
  color: #333;
}
</style>
```
