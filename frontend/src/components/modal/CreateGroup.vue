<script setup lang="ts">
import { ref } from 'vue'
import { createGroups } from '@/api'
import { useViewUiStore, useSoucreStore } from '@/stores'

const formValue = ref({})

const rules = {
  name: {
    required: true,
    message: '图源组名称',
    trigger: 'blur',
  },
}

async function handleClick() {
  await createGroups(formValue.value.name)
  await useSoucreStore().fetchGroupsAndSources()
  formValue.value = {}
  useViewUiStore().closeModal()
}
</script>

<template>
  <n-card title="新建图源组">
    <n-form :label-width="80" :model="formValue" :rules="rules" size="small" label-placement="left">
      <n-form-item label="名称" label-width="auto">
        <n-input v-model:value="formValue.name" placeholder="请输入图源组名称" />
      </n-form-item>
      <n-form-item style="display: flex; justify-content: end">
        <n-button type="primary" @click="handleClick">创建</n-button>
      </n-form-item>
    </n-form>
  </n-card>
</template>

<style scoped></style>
