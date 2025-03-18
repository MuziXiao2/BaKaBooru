<script setup lang="ts">
import { ref } from 'vue'
import { addSource } from '@/api'
import { useSoucreStore } from '@/stores/common/source.ts'
import { useViewUiStore } from '@/stores'

const formValue = ref<{
  url: string
  type: string
  groupId: string
}>({})
const sourceStore = useSoucreStore()

const rules = {
  url: {
    required: true,
    message: '图源链接',
    trigger: 'blur',
  },
  type: {
    required: true,
    message: '图源类型',
    trigger: 'blur',
  },
  group: {
    required: true,
    message: '图源分组',
    trigger: 'blur',
  },
}

async function handleClick() {
  await addSource(formValue.value)
  await useSoucreStore().fetchGroupsAndSources()
  useViewUiStore().closeModal()
}
</script>

<template>
  <n-card title="添加源">
    <n-form
      :model="formValue"
      :rules="rules"
      size="small"
      label-placement="left"
      label-width="auto"
    >
      <n-form-item label="URL">
        <n-input v-model:value="formValue.url" placeholder="请输入URL" />
      </n-form-item>

      <n-form-item label="类型">
        <n-select
          v-model:value="formValue.type"
          placeholder="请选择类型"
          :options="sourceStore.sourceTypeSelectOptions"
        />
      </n-form-item>

      <n-form-item label="图源组">
        <n-select
          v-model:value="formValue.groupId"
          placeholder="请选择组"
          :options="sourceStore.groupSelectOptions"
        />
      </n-form-item>

      <n-form-item style="display: flex; justify-content: end">
        <n-button type="primary" @click="handleClick">创建</n-button>
      </n-form-item>
    </n-form>
  </n-card>
</template>

<style scoped></style>
