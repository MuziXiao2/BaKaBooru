<script setup lang="ts">
import { ref } from 'vue'
import { createGroup } from '@/api'
import { useModalStore, useSoucreStore } from '@/stores'

const formValue = ref<{ name: string | null }>({ name: null })

const rules = {
  name: {
    required: true,
    message: '图源组名称',
    trigger: 'blur',
  },
}
const modalStore = useModalStore()

async function handleClick() {
  if (formValue.value.name) {
    await createGroup(formValue.value.name)
    formValue.value = { name: null }
    await useSoucreStore().update()
    modalStore.closeModal()
  } else {
  }
}
</script>

<template>
  <n-card title="新建组">
    <n-form :label-width="80" :model="formValue" :rules="rules" size="small" label-placement="left">
      <n-form-item label="名称" label-width="auto">
        <n-input v-model:value="formValue.name" placeholder="请输入组名" />
      </n-form-item>
      <n-form-item style="display: flex; justify-content: end">
        <n-button type="primary" @click="handleClick">创建</n-button>
      </n-form-item>
    </n-form>
  </n-card>
</template>

<style scoped></style>
