<template>
  <el-form
    :model="searchFormStore.form"
    @submit.prevent="onSubmit"
    class="form-container"
    aria-label="图片搜索表单"
  >
    <!-- 关键词搜索 -->
    <el-input
      v-model="searchFormStore.form.keyword"
      placeholder="搜索图片标题"
      clearable
      class="search-keyword"
      aria-label="搜索图片标题"
      @input="onSubmit"
      @change="onSubmit"
    >
    </el-input>

    <!-- 创建时间范围 -->
    <el-date-picker
      v-model="searchFormStore.form.createdAt"
      type="daterange"
      start-placeholder="起始日期"
      end-placeholder="结束日期"
      unlink-panels
      clearable
      class="search-date"
      aria-label="选择图片创建日期范围"
    />

    <!-- 更新时间范围 -->
    <el-date-picker
      v-model="searchFormStore.form.updatedAt"
      type="daterange"
      start-placeholder="起始日期"
      end-placeholder="结束日期"
      unlink-panels
      clearable
      class="search-date"
      aria-label="选择图片更新日期范围"
      @change="onSubmit"
    />

    <!-- 排序字段 -->
    <el-select
      v-model="searchFormStore.form.sortBy"
      class="search-sort"
      aria-label="选择排序规则"
      :default-first-option="true"
      @change="onSubmit"
    >
      <template #prefix>
        <span class="sort-prefix">排序方式</span>
      </template>
      <el-option label="最佳匹配" value="title" />
      <el-option label="创建日期" value="createdAt" />
      <el-option label="更新日期" value="updatedAt" />
    </el-select>

    <!-- 排序方向按钮 -->
    <el-button
      :icon="searchFormStore.form.sortDirection === 'asc' ? ArrowUp : ArrowDown"
      class="sort-direction"
      aria-label="切换排序方向"
      @click="toggleSortDirection"
    />

    <!-- 重置按钮 -->
    <div class="search-actions">
      <el-button @click="onReset" aria-label="重置搜索条件">重置</el-button>
    </div>
  </el-form>
</template>

<script setup lang="ts">
import { ArrowUp, ArrowDown } from '@element-plus/icons-vue'
import { debounce } from 'lodash-es'
import { useImageStore } from '@/stores/useImageStore'
import { useSearchFormStore } from '@/stores/useSearchFormStore'
import { usePaginationStore } from '@/stores/usePaginationStore'

const imageStore = useImageStore()
const searchFormStore = useSearchFormStore()
const paginationStore = usePaginationStore()

const onSubmit = debounce(() => {
  paginationStore.goToPage(1)
  imageStore.fetchImages()
}, 300)

const onReset = debounce(() => {
  searchFormStore.resetForm()
  paginationStore.goToPage(1)
  imageStore.fetchImages()
}, 300)

const toggleSortDirection = () => {
  searchFormStore.form.sortDirection = searchFormStore.form.sortDirection === 'asc' ? 'desc' : 'asc'
  onSubmit()
}
</script>

<style scoped>
.form-container {
  display: flex;
  width: 100%;
  height: 100%;
  gap: 0 32px;
  align-items: center;
  box-sizing: border-box;
}

/* 关键词搜索框 */
.search-keyword {
  flex: 1 1 300px; /* 优先显示更大宽度 */
  min-width: 240px;
  max-width: 480px;
}

/* 创建/更新时间选择器 */
.search-date {
  flex: 1 1 260px;
  min-width: 220px;
  max-width: 360px;
}

/* 排序选择器 */
.search-sort {
  flex: 0 0 200px; /* 固定宽度 */
}

/* 排序方向按钮 */
.sort-direction {
  flex: 0 0 40px;
  width: 40px;
  height: 40px;
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 重置按钮区域，独占一行 */
.search-actions {
  display: flex;
  justify-content: flex-end;
  flex: 1 1 100%;
  gap: 12px;
  margin-top: 8px;
}
</style>
