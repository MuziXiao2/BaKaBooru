<script setup lang="ts">
import { ref } from 'vue'

const formValue = ref({})

const rules = {
  name: {
    required: true,
    message: '图源名称',
    trigger: 'blur',
  },
  type: {
    required: true,
    message: '图源类型',
    trigger: 'blur',
  },
  url: {
    required: true,
    message: '图源链接',
    trigger: 'blur',
  },
  group: {
    required: true,
    message: '图源分组',
    trigger: 'blur',
  },
}

const generalOptions = [
  {
    label: "local",
    value: 'local',
  },
  {
    label: 'network',
    value: 'network',
  },
]

async function handleClick() {}
</script>

<template>
  <n-card title="添加图源">
    <n-form :label-width="80" :model="formValue" :rules="rules" size="small" label-placement="left">
      <n-form-item label="名称" label-width="auto">
        <n-input v-model:value="formValue.name" placeholder="请输入图源名称" />
      </n-form-item>
      <n-form-item label="类型" label-width="auto">
        <n-select
          v-model:value="formValue.type"
          placeholder="请选择图源类型"
          :options="generalOptions"
        />
      </n-form-item>

      <n-form-item style="display: flex; justify-content: end">
        <n-button type="primary" @click="handleClick">创建</n-button>
      </n-form-item>
    </n-form>
  </n-card>
</template>

<style scoped></style>
