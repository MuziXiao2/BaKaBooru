<script setup lang="ts">
import { computed, onMounted, ref } from 'vue'
import { addSource } from '@/api'
import { useSoucreStore } from '@/stores/common/source.ts'
import { useModalStore } from '@/stores'
import type { Group, SourceReferenceDTO } from '@/types'

const soucreStore = useSoucreStore()
const modalStore = useModalStore()
const rules = {
  groupId: {
    required: true,
    message: '组',
    trigger: 'blur',
  },
  url: {
    required: true,
    message: '图源URL',
    trigger: 'blur',
  },
}

const formValue = ref<SourceReferenceDTO>({ groupId: null, url: null })

onMounted(async () => {
  await soucreStore.update()
})

const groupSelectOptions = computed(
  () =>
    soucreStore.groups?.map((group: Group) => ({
      label: group.name,
      value: group.id,
    })) || [],
)

async function handleClick() {
  if (formValue.value.groupId && formValue.value.url) {
    await addSource(formValue.value)
    await soucreStore.update()
    modalStore.closeModal()
  } else {
    console.log('fuck')
  }
}
</script>

<template>
  <n-card title="添加图源">
    <n-form
      :model="formValue"
      :rules="rules"
      size="small"
      label-placement="left"
      label-width="auto"
    >
      <n-form-item label="组">
        <n-select
          v-model:value="formValue.groupId"
          placeholder="请选择组"
          :options="groupSelectOptions"
        />
      </n-form-item>
      <n-form-item label="URL">
        <n-input v-model:value="formValue.url" placeholder="请输入URL" />
      </n-form-item>
      <n-form-item style="display: flex; justify-content: end">
        <n-button type="primary" @click="handleClick">创建</n-button>
      </n-form-item>
    </n-form>
  </n-card>
</template>

<style scoped></style>
