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
            <template #prefix>
              <span class="sort-prefix">排序方式</span>
            </template>
            <el-option label="最佳匹配" value="title" />
            <el-option label="创建日期" value="createdAt" />
            <el-option label="更新日期" value="updatedAt" />
          </el-select>

          <el-tooltip
            :content="
              searchFormStore.form.sortDirection === 'asc'
                ? '当前为升序，点击切换为降序'
                : '当前为降序，点击切换为升序'
            "
            placement="top"
          >
            <el-button
              :type="searchFormStore.form.sortDirection === 'asc' ? 'primary' : 'default'"
              :icon="searchFormStore.form.sortDirection === 'asc' ? ArrowUp : ArrowDown"
              class="sort-direction"
              aria-label="切换排序方向"
              @click="toggleSortDirection"
            >
              {{ searchFormStore.form.sortDirection === 'asc' ? '升序' : '降序' }}
            </el-button>
          </el-tooltip>
        </div>
      </div>

      <!-- 标签过滤器 -->
      <div class="tag-section">
        <label class="section-label">标签筛选</label>

        <!-- 画师标签 -->
        <div class="tag-category">
          <div class="category-header">
            <span class="category-title">画师标签</span>
            <el-button type="text" size="small" @click="clearTagsByCategory('artist')"
              >清空
            </el-button>
          </div>
          <el-input
            v-model="tagInputs.artist"
            placeholder="输入画师标签，按回车添加"
            @keyup.enter="handleTagAdd('artist')"
            clearable
          />
          <div class="tag-list" v-if="searchFormStore.form?.tags?.artist?.length">
            <el-tag
              v-for="(tag, index) in searchFormStore.form?.tags?.artist || []"
              :key="index"
              closable
              @close="removeTag('artist', index)"
              size="small"
              type="warning"
            >
              {{ tag }}
            </el-tag>
          </div>
        </div>

        <!-- 角色标签 -->
        <div class="tag-category">
          <div class="category-header">
            <span class="category-title">角色标签</span>
            <el-button type="text" size="small" @click="clearTagsByCategory('character')"
              >清空
            </el-button>
          </div>
          <el-input
            v-model="tagInputs.character"
            placeholder="输入角色标签，按回车添加"
            @keyup.enter="handleTagAdd('character')"
            clearable
          />
          <div class="tag-list" v-if="searchFormStore.form?.tags?.character?.length">
            <el-tag
              v-for="(tag, index) in searchFormStore.form?.tags?.character || []"
              :key="index"
              closable
              @close="removeTag('character', index)"
              size="small"
              type="success"
            >
              {{ tag }}
            </el-tag>
          </div>
        </div>

        <!-- 版权标签 -->
        <div class="tag-category">
          <div class="category-header">
            <span class="category-title">版权标签</span>
            <el-button type="text" size="small" @click="clearTagsByCategory('copyright')"
              >清空
            </el-button>
          </div>
          <el-input
            v-model="tagInputs.copyright"
            placeholder="输入版权标签，按回车添加"
            @keyup.enter="handleTagAdd('copyright')"
            clearable
          />
          <div class="tag-list" v-if="searchFormStore.form?.tags?.copyright?.length">
            <el-tag
              v-for="(tag, index) in searchFormStore.form?.tags?.copyright || []"
              :key="index"
              closable
              @close="removeTag('copyright', index)"
              size="small"
              type="danger"
            >
              {{ tag }}
            </el-tag>
          </div>
        </div>

        <!-- 元信息标签 -->
        <div class="tag-category">
          <div class="category-header">
            <span class="category-title">元信息标签</span>
            <el-button type="text" size="small" @click="clearTagsByCategory('meta')"
              >清空
            </el-button>
          </div>
          <el-input
            v-model="tagInputs.meta"
            placeholder="输入元信息标签，按回车添加"
            @keyup.enter="handleTagAdd('meta')"
            clearable
          />
          <div class="tag-list" v-if="searchFormStore.form?.tags?.meta?.length">
            <el-tag
              v-for="(tag, index) in searchFormStore.form?.tags?.meta || []"
              :key="index"
              closable
              @close="removeTag('meta', index)"
              size="small"
              type="info"
            >
              {{ tag }}
            </el-tag>
          </div>
        </div>

        <!-- 通用标签 -->
        <div class="tag-category">
          <div class="category-header">
            <span class="category-title">通用标签</span>
            <el-button type="text" size="small" @click="clearTagsByCategory('general')"
              >清空
            </el-button>
          </div>
          <el-input
            v-model="tagInputs.general"
            placeholder="输入通用标签，按回车添加"
            @keyup.enter="handleTagAdd('general')"
            clearable
          />
          <div class="tag-list" v-if="searchFormStore.form?.tags?.general?.length">
            <el-tag
              v-for="(tag, index) in searchFormStore.form?.tags?.general || []"
              :key="index"
              closable
              @close="removeTag('general', index)"
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
import { reactive } from 'vue'
import { ArrowUp, ArrowDown } from '@element-plus/icons-vue'
import { debounce } from 'lodash-es'
import { useImageStore } from '@/stores/useImageStore'
import { useSearchFormStore } from '@/stores/useSearchFormStore'
import type { TagCategory } from '@/types'

const imageStore = useImageStore()
const searchFormStore = useSearchFormStore()

// 定义标签类别类型

// 初始化表单中的标签数组
searchFormStore.form = searchFormStore.form || {}
searchFormStore.form.tags = searchFormStore.form.tags || {
  artist: [],
  character: [],
  copyright: [],
  meta: [],
  general: [],
}

// 标签输入状态
const tagInputs = reactive({
  artist: '',
  character: '',
  copyright: '',
  meta: '',
  general: '',
})

// 处理标签添加
const handleTagAdd = (category: TagCategory) => {
  const value = tagInputs[category].trim()
  const tags = searchFormStore.form.tags[category]
  if (value && !tags?.includes(value)) {
    searchFormStore.form.tags[category].push(value)
    tagInputs[category] = ''
    onSubmit()
  }
}

// 移除标签
const removeTag = (category: TagCategory, index: number) => {
  if (searchFormStore.form?.tags?.[category]) {
    searchFormStore.form.tags[category].splice(index, 1)
    onSubmit()
  }
}

// 清空特定类别的标签
const clearTagsByCategory = (category: TagCategory) => {
  if (searchFormStore.form?.tags) {
    searchFormStore.form.tags[category] = []
    onSubmit()
  }
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
  background-color: #f9f9f9;
  border-right: 1px solid #ddd;
}

.title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 16px;
  display: block;
}

.form-container {
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.section-label {
  display: block;
  font-size: 14px;
  color: #606266;
  margin-bottom: 8px;
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

.autocomplete {
  width: 100%;
}

.selected-tags {
  margin-top: 8px;
}

.tag-list {
  margin-top: 8px;
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
}

.clear-btn {
  color: #f56c6c;
  font-size: 13px;
  padding: 0;
}

.sort-container {
  display: flex;
  gap: 8px;
  align-items: stretch;
}

.search-sort {
  flex: 1;
}

.sort-direction {
  min-width: 85px;
  height: auto;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 4px;
  padding: 8px 15px;
}

.sort-direction :deep(.el-icon) {
  margin: 0;
}

.sort-prefix {
  color: #909399;
  font-size: 14px;
}

.search-actions {
  display: flex;
  justify-content: flex-end;
  margin-top: 8px;
}

.tag-category {
  margin-bottom: 16px;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  padding: 12px;
}

.category-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 8px;
}

.category-title {
  font-weight: bold;
  color: #606266;
}

.el-tag {
  margin-right: 6px;
  margin-bottom: 6px;
}
</style>
