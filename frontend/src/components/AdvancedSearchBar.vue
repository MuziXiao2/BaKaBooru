<template>
  <el-form
    :model="store.form"
    @submit.prevent="onSubmit"
    class="form-container"
    aria-label="图片搜索表单"
  >
    <!-- 搜索项 -->
    <div class="form-item">
      <el-input
        v-model="store.form.keyword"
        placeholder="搜索图片标题"
        clearable
        aria-label="搜索图片标题"
      >
        <template #append>
          <el-button
            :icon="Search"
            type="primary"
            @click="onSubmit"
            aria-label="执行搜索"
          />
        </template>
      </el-input>
    </div>

    <!-- 时间范围 -->
    <div class="form-item">
      <el-date-picker
        v-model="store.form.createTime"
        type="daterange"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        unlink-panels
        clearable
        aria-label="选择图片创建日期范围"
      />
    </div>

    <!-- 排序规则 -->
    <div class="form-item sort-select">
      <el-select
        v-model="store.form.sort"
        placeholder="排序规则"
        clearable
        aria-label="选择排序规则"
      >
        <el-option label="更新日期" value="updateDate" />
        <el-option label="上传日期" value="uploadDate" />
        <el-option label="标题匹配度" value="titleMatch" />
      </el-select>
    </div>

    <!-- 操作按钮 -->
    <div class="form-actions">
      <el-button type="primary" @click="onSubmit" aria-label="提交搜索">
        搜索
      </el-button>
      <el-button @click="onReset" aria-label="重置搜索条件">
        重置
      </el-button>
    </div>
  </el-form>
</template>

<script setup lang="ts">
import { Search } from '@element-plus/icons-vue'
import { useImageSearchStore } from '@/stores/useImageSearchStore'
import { debounce } from 'lodash-es'

const store = useImageSearchStore()

const onSubmit = debounce(() => {
  store.goToPage(1) // 设置为第 1 页
  store.fetchImages() // 触发查询
}, 300)

const onReset = debounce(() => {
  store.resetForm() // 重置表单和分页
  store.goToPage(1) // 设置为第 1 页
  store.fetchImages() // 触发查询
}, 300)
</script>

<style scoped>
.form-container {
  display: flex;
  flex-wrap: wrap;
  gap: 16px 20px;
  align-items: flex-end;
}

.form-item {
  max-width: 300px;
  flex: 1 1 auto;
  min-width: 180px;
}

.form-item.sort-select {
  max-width: 200px;
}

.form-actions {
  display: flex;
  gap: 12px;
}
</style>
