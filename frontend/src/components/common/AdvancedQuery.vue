<template>
  <div class="tag-filter-container">
    <label class="title">高级搜索</label>

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
        <el-autocomplete
          class="autocomplete"
          v-model="state"
          clearable
          :fetch-suggestions="querySearch"
          :trigger-on-focus="false"
          placeholder="输入标签名称，按回车添加多个标签"
          @select="handleSelect"
          :fit-input-width="true"
          @keyup.enter="handleEnter"
        />

        <div v-if="selectedTags.length" class="selected-tags">
          <div class="tag-list">
            <el-tag
              v-for="(tag, index) in selectedTags"
              :key="index"
              closable
              @close="removeTag(index)"
              size="small"
            >
              {{ tag }}
            </el-tag>
          </div>
          <el-button type="text" class="clear-btn" @click="clearTags"> 清空标签 </el-button>
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
import { onMounted, ref } from 'vue'
import { ArrowUp, ArrowDown } from '@element-plus/icons-vue'
import { debounce } from 'lodash-es'
import { useImageStore } from '@/stores/useImageStore'
import { useSearchFormStore } from '@/stores/useSearchFormStore'

interface TagSuggestion {
  value: string
  link: string
}

const imageStore = useImageStore()
const searchFormStore = useSearchFormStore()

const state = ref('')
const restaurants = ref<TagSuggestion[]>([])
const selectedTags = ref<string[]>([])

const querySearch = (queryString: string, cb: (suggestions: TagSuggestion[]) => void) => {
  const results = queryString
    ? restaurants.value.filter(createFilter(queryString))
    : restaurants.value
  cb(results)
}

const createFilter = (queryString: string) => {
  return (restaurant: TagSuggestion) => {
    return restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
  }
}

const loadAll = (): TagSuggestion[] => {
  return [
    { value: 'vue', link: 'https://github.com/vuejs/vue' },
    { value: 'element', link: 'https://github.com/ElemeFE/element' },
    { value: 'cooking', link: 'https://github.com/ElemeFE/cooking' },
    { value: 'mint-ui', link: 'https://github.com/ElemeFE/mint-ui' },
    { value: 'vuex', link: 'https://github.com/vuejs/vuex' },
    { value: 'vue-router', link: 'https://github.com/vuejs/vue-router' },
    { value: 'babel', link: 'https://github.com/babel/babel' },
  ]
}

const handleSelect = (item: TagSuggestion) => {
  addTag(item.value)
}

const handleEnter = () => {
  const trimmed = state.value.trim()
  if (trimmed && !selectedTags.value.includes(trimmed)) {
    addTag(trimmed)
  }
}

const addTag = (tag: string) => {
  if (!selectedTags.value.includes(tag)) {
    selectedTags.value.push(tag)
    onSubmit()
  }
  state.value = ''
}

const removeTag = (index: number) => {
  selectedTags.value.splice(index, 1)
  onSubmit()
}

const clearTags = () => {
  selectedTags.value = []
  onSubmit()
}

const onSubmit = debounce(() => {
  imageStore.queryImages()
  paginationStore.updateTotalPages()
}, 300)

const onReset = debounce(() => {
  searchFormStore.resetForm()
  selectedTags.value = []
  imageStore.queryImages()
  paginationStore.updateTotalPages()
}, 300)

const toggleSortDirection = () => {
  searchFormStore.form.sortDirection = searchFormStore.form.sortDirection === 'asc' ? 'desc' : 'asc'
  onSubmit()
}

onMounted(() => {
  restaurants.value = loadAll()
})
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
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
  margin-bottom: 8px;
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
</style>
