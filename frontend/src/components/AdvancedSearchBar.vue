<template>
  <el-form
    :model="store.form"
    @submit.prevent="onSubmit"
    class="form-container"
    aria-label="图片搜索表单"
  >
    <!-- 关键词搜索 -->
    <el-input
      v-model="store.form.keyword"
      placeholder="搜索图片标题"
      clearable
      class="search-keyword"
      aria-label="搜索图片标题"
    >
      <template #append>
        <el-button :icon="Search" type="primary" @click="onSubmit" aria-label="执行搜索" />
      </template>
    </el-input>

    <!-- 创建时间范围 -->
    <el-date-picker
      v-model="store.form.createTime"
      type="daterange"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      unlink-panels
      clearable
      class="search-date"
      aria-label="选择图片创建日期范围"
      @change="onSubmit"
    />

    <!-- 排序选择 -->
    <el-select
      v-model="store.form.sort"
      clearable
      class="search-sort"
      aria-label="选择排序规则"
      :default-first-option="true"
      @change="onSubmit"
    >
      <template #prefix>
        <span class="sort-prefix">排序方式</span>
      </template>
      <el-option label="最佳匹配" value="titleMatch" />
      <el-option label="更新日期" value="updateDate" />
      <el-option label="上传日期" value="uploadDate" />
    </el-select>

    <!-- 重置按钮 -->
    <div class="search-actions">
      <el-button @click="onReset" aria-label="重置搜索条件">重置</el-button>
    </div>
  </el-form>
</template>
<script setup lang="ts">
import { Search } from '@element-plus/icons-vue'
import { debounce } from 'lodash-es'
import { useImageSearchStore } from '@/stores/useImageSearchStore'

const store = useImageSearchStore()

const onSubmit = debounce(() => {
  store.goToPage(1)
  store.fetchImages()
}, 300)

const onReset = debounce(() => {
  store.resetForm()
  store.goToPage(1)
  store.fetchImages()
}, 300)
</script>
<style scoped>
.form-container {
  display: flex;
  gap: 16px 20px;
  align-items: flex-end;
}

.search-keyword {
  width: 400px;
  flex: 1 1 200px;
}

.search-date {
  max-width: 320px;
  flex: 1 1 240px;
}

.search-sort {
  max-width: 200px;
  flex: 1 1 160px;
}

.sort-prefix {
  color: #606266;
  font-size: 14px;
  padding-right: 6px;
  white-space: nowrap;
  display: inline-block;
}

.search-actions {
  display: flex;
  gap: 12px;
  flex: 1 1 auto;
  min-width: 100px;
}
</style>
