<script setup lang="ts">
import { computed, onMounted, ref } from 'vue'
import { addSource } from '@/api'
import { useSoucreStore } from '@/stores/common/source.ts'
import { useModalStore } from '@/stores'
import type { Group, SourceReferenceDTO } from '@/types'

const sourceStore = useSoucreStore()
const modalStore = useModalStore()

const formValue = ref<SourceReferenceDTO>({
  groupId: null,
  name: null,
  url: null,
})

onMounted(async () => {
  await sourceStore.update()
})

const groupSelectOptions = computed(
  () =>
    sourceStore.groups?.map((group: Group) => ({
      label: group.name,
      value: group.id,
    })) || [],
)

async function handleClick() {
  if (formValue.value.groupId && formValue.value.name && formValue.value.url) {
    await addSource(formValue.value)
    await sourceStore.update()
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
      <n-form-item label="名称" label-width="auto">
        <n-input v-model:value="formValue.name" placeholder="请输入图源名称" />
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
