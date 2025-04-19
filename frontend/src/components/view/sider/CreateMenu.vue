<script setup lang="ts">
import { AddCircleOutline as AddIcon } from '@vicons/ionicons5'
import type { DropdownOption } from 'naive-ui'
import { useModal } from 'naive-ui'
import { useModalStore} from '@/stores'

const options: DropdownOption[] = [
  { label: '新建组', key: 'CreateGroup' },
  { label: '创建图源', key: 'CreateSource' },
  { label: '添加图源', key: 'AddSource' },
]

const modal = useModal()
const modalStore = useModalStore()

function handleSelect(key: string) {
  switch (key) {
    case 'CreateGroup':
      modalStore.openModal(modal, 'CreateGroup')
      break
    case 'AddSource':
      modalStore.openModal(modal, 'AddSource')
      break
    default:
      break
  }
}
</script>

<template>
  <n-dropdown trigger="click" :options="options" @select="handleSelect" placement="right-start">
    <n-button text>
      <n-icon size="25">
        <AddIcon />
      </n-icon>
    </n-button>
  </n-dropdown>
</template>

<style scoped></style>
