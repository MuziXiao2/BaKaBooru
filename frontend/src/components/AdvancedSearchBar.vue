<template>
  <el-form :model="form" @submit.prevent="onSubmit" class="form-container">
    <!-- 关键词输入 -->
    <div class="form-item">
      <el-input v-model="form.keyword" placeholder="搜索图片标题" class="input-with-select">
        <template #append>
          <el-button :icon="Search" />
        </template>
      </el-input>
    </div>

    <!-- 创建时间 -->
    <div class="form-item">
      <el-date-picker
        v-model="form.createTime"
        type="daterange"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        unlink-panels
        clearable
      />
    </div>

    <!-- 排序选择 -->
    <div class="form-item sort-select">
      <el-select v-model="form.sort" placeholder="排序规则">
        <el-option label="更新日期" value="updateDate" />
        <el-option label="上传日期" value="uploadDate" />
        <el-option label="标题匹配度" value="titleMatch" />
      </el-select>
    </div>

    <!-- 操作按钮 -->
    <div class="form-actions">
      <el-button type="primary" @click="onSubmit">搜索</el-button>
      <el-button @click="onReset">重置</el-button>
    </div>
  </el-form>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { Search } from '@element-plus/icons-vue'

const form = ref({
  keyword: '',
  color: '',
  tag: '',
  grade: '',
  width: '',
  height: '',
  createTime: [],
  sort: '', // 排序字段
})

const fetchKeywordSuggestions = async (queryString: string, cb: Function) => {
  if (!queryString) return cb([])
  const res = await fetch(`/api/keyword-suggest?q=${encodeURIComponent(queryString)}`)
  const data = await res.json()
  cb(data.map((item: string) => ({ value: item })))
}

const onSubmit = () => {
  console.log('搜索表单提交', form.value)
  // emit('search', form.value) 可向父组件传递数据
}

const onReset = () => {
  form.value = {
    keyword: '',
    color: '',
    tag: '',
    grade: '',
    width: '',
    height: '',
    createTime: [],
    sort: '',
  }
}
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

@media (max-width: 640px) {
  .form-container {
    flex-direction: column;
    align-items: stretch;
  }

  .form-actions {
    justify-content: flex-start;
  }
}
</style>
