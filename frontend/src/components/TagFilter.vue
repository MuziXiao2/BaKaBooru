<template>
  <div class="tag-filter-container">
    <label class="title">标签过滤器</label>

    <el-autocomplete
      class="autocomplete"
      v-model="state"
      clearable
      :fetch-suggestions="querySearch"
      :trigger-on-focus="false"
      placeholder="输入标签进行过滤"
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
      <el-button type="text" icon="el-icon-delete" class="clear-btn" @click="clearTags">
        清空
      </el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'

interface RestaurantItem {
  value: string
  link: string
}

const state = ref('')
const restaurants = ref<RestaurantItem[]>([])
const selectedTags = ref<string[]>([])

const querySearch = (queryString: string, cb: any) => {
  const results = queryString
    ? restaurants.value.filter(createFilter(queryString))
    : restaurants.value
  cb(results)
}

const createFilter = (queryString: string) => {
  return (restaurant: RestaurantItem) => {
    return restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
  }
}

const loadAll = (): RestaurantItem[] => {
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

const handleSelect = (item: RestaurantItem) => {
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
  }
  state.value = ''
}

const removeTag = (index: number) => {
  selectedTags.value.splice(index, 1)
}

const clearTags = () => {
  selectedTags.value = []
}

onMounted(() => {
  restaurants.value = loadAll()
})
</script>

<style scoped>
.tag-filter-container {
  padding: 12px;
  background-color: #f9f9f9;
  border-right: 1px solid #ddd;
}

.title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;
}

.autocomplete {
  width: 100%;
}

.selected-tags {
  margin-top: 10px;
}

.tag-list {
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
}

.clear-btn {
  margin-top: 6px;
  color: #f56c6c;
  font-size: 13px;
}
</style>
