<script setup lang="ts">
import { AddCircleOutline as AddIcon } from '@vicons/ionicons5'
import type { DropdownOption } from 'naive-ui'
import { useModal } from 'naive-ui'
import { useViewUiStore } from '@/stores'

const options: DropdownOption[] = [
  { label: '添加图源', key: 'AddSource' },
  { label: '新建组', key: 'CreateGroup' },
]

const modal = useModal()

function handleSelect(key: string) {
  const viewUiStore = useViewUiStore()

  switch (key) {
    case 'AddSource':
      viewUiStore.openModal(modal, 'AddSource')
      break
    case 'CreateGroup':
      viewUiStore.openModal(modal, 'CreateGroup')
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
