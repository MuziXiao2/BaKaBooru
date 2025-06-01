<template>
  <div class="tag-filter-container">
    <label class="title">搜索</label>

    <el-form
      :model="searchFormStore.form"
      @submit.prevent="onSubmit"
      class="form-container"
      aria-label="图片搜索表单"
    >
      <!-- 关键词搜索 -->
      <div class="search-section">
        <label class="section-label">标题搜索</label>
        <el-input
          v-model="searchFormStore.form.keyword"
          placeholder="请输入要搜索的图片标题关键词"
          clearable
          class="search-keyword"
          aria-label="搜索图片标题"
          @input="onSubmit"
          @change="onSubmit"
        />
      </div>

      <!-- 排序选项 -->
      <div class="sort-section">
        <label class="section-label">排序设置</label>
        <div class="sort-container">
          <el-select
            v-model="searchFormStore.form.sortBy"
            class="search-sort"
            aria-label="选择排序规则"
            :default-first-option="true"
            @change="onSubmit"
          >
            <el-option label="最佳匹配" value="title" />
            <el-option label="创建日期" value="createdAt" />
            <el-option label="更新日期" value="updatedAt" />
          </el-select>

          <el-button
            :type="searchFormStore.form.sortDirection === 'asc' ? 'primary' : 'default'"
            :icon="searchFormStore.form.sortDirection === 'asc' ? ArrowUp : ArrowDown"
            class="sort-direction"
            aria-label="切换排序方向"
            @click="toggleSortDirection"
          >
            {{ searchFormStore.form.sortDirection === 'asc' ? '升序' : '降序' }}
          </el-button>
        </div>
      </div>

      <!-- 标签过滤器 -->
      <div class="tag-section">
        <label class="section-label">标签筛选</label>
        <div class="tag-input-container">
          <el-input
            v-model="tagInput"
            placeholder="输入标签，用空格分隔，按回车提交"
            @keyup.enter="handleTagsSubmit"
            clearable
          />
          <div class="tag-list" v-if="searchFormStore.form.tags.length">
            <el-tag
              v-for="(tag, index) in searchFormStore.form.tags"
              :key="index"
              closable
              @close="removeTag(index)"
              size="small"
            >
              {{ tag }}
            </el-tag>
          </div>
        </div>
      </div>

      <!-- 操作按钮 -->
      <div class="search-actions">
        <el-button @click="onReset" aria-label="重置搜索条件">重置所有</el-button>
      </div>
    </el-form>
  </div>
</template>
<script setup lang="ts">
import { ref } from 'vue'
import { ArrowUp, ArrowDown } from '@element-plus/icons-vue'
import { debounce } from 'lodash-es'
import { useImageStore } from '@/stores/useImageStore.ts'
import { useQueryFormStore } from '@/stores/useQueryFormStore.ts'

const imageStore = useImageStore()
const searchFormStore = useQueryFormStore()

// 标签输入状态
const tagInput = ref('')

// 处理标签提交
const handleTagsSubmit = () => {
  const newTags = tagInput.value
    .trim()
    .split(/\s+/)
    .filter(Boolean)
    .reduce((unique: string[], tag: string) => {
      if (!searchFormStore.form.tags.includes(tag) && !unique.includes(tag)) {
        unique.push(tag)
      }
      return unique
    }, [])

  if (newTags.length > 0) {
    searchFormStore.form.tags.push(...newTags)
    tagInput.value = ''
    onSubmit()
  }
}

// 移除标签
const removeTag = (index: number) => {
  searchFormStore.form.tags.splice(index, 1)
  onSubmit()
}

const onSubmit = debounce(() => {
  imageStore.queryImages()
}, 300)

const onReset = debounce(() => {
  searchFormStore.resetForm()
  imageStore.queryImages()
}, 300)

const toggleSortDirection = () => {
  if (searchFormStore.form?.sortDirection !== undefined) {
    searchFormStore.form.sortDirection =
      searchFormStore.form.sortDirection === 'asc' ? 'desc' : 'asc'
    onSubmit()
  } else if (searchFormStore.form) {
    searchFormStore.form.sortDirection = 'desc'
    onSubmit()
  }
}
</script>

<style scoped>
.tag-filter-container {
  padding: 16px;
  background-color: var(--el-bg-color);
  height: 100%;
  position: relative;
  display: flex;
  flex-direction: column;
}

.title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 16px;
  color: var(--el-text-color-primary);
  display: block;
}

.form-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
  flex: 1;
  overflow-y: auto;
  padding-bottom: 60px;
}

.section-label {
  display: block;
  font-size: 14px;
  color: var(--el-text-color-regular);
  margin-bottom: 8px;
  font-weight: 500;
}

.search-section,
.sort-section,
.tag-section {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.search-keyword {
  width: 100%;
}

.search-keyword :deep(.el-input__inner) {
  height: 36px;
  font-size: 14px;
}

.sort-container {
  display: flex;
  gap: 8px;
  align-items: stretch;
  height: 36px;
}

.search-sort {
  flex: 1;

}

.search-sort :deep(.el-input__wrapper) {
  height: 36px;
  line-height: 36px;
}

.search-sort :deep(.el-input__inner) {
  height: 36px;
  line-height: 36px;
}

.sort-direction {
  min-width: 90px;
  height: 32px;
  line-height: 1;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 4px;
  padding: 0 15px;
  font-size: 14px;
  margin: 0;
}

.sort-direction :deep(.el-icon) {
  margin: 0;
  font-size: 16px;
}

.sort-prefix {
  color: var(--el-text-color-secondary);
  font-size: 14px;
}

.tag-input-container {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.tag-input-container :deep(.el-input__inner) {
  height: 36px;
  font-size: 14px;
}

.tag-list {
  min-height: 36px;
  max-height: 150px;
  overflow-y: auto;
  padding: 8px 0;
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  align-content: flex-start;
}

.tag-list::-webkit-scrollbar {
  width: 6px;
}

.tag-list::-webkit-scrollbar-thumb {
  background-color: var(--el-border-color);
  border-radius: 3px;
}

.tag-list::-webkit-scrollbar-track {
  background-color: transparent;
}

.el-tag {
  margin: 0;
  transition: all 0.2s ease;
  height: 28px;
  padding: 0 10px;
  font-size: 13px;
}

.el-tag :deep(.el-tag__close) {
  right: -2px;
  font-size: 12px;
}

.el-tag:hover {
  transform: translateY(-1px);
}

.search-actions {
  position: absolute;
  bottom: 16px;
  right: 16px;
  padding: 0;
  display: flex;
  justify-content: flex-end;
  z-index: 1;
}

.search-actions .el-button {
  min-width: 90px;
  height: 36px;
  font-size: 14px;
}
</style>
