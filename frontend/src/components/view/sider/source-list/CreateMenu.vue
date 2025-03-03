<script setup lang="ts">
import { AddCircleOutline as AddIcon } from '@vicons/ionicons5'
import type { DropdownOption } from 'naive-ui'
import { useModal } from 'naive-ui'
import { useViewUiStore } from '@/stores'
import { h } from 'vue'
import AddSource from '@/components/modal/AddSource.vue'
import CreateSourceGroup from '@/components/modal/CreateSourceGroup.vue'

const options: DropdownOption[] = [
  { label: '添加图源', key: 'addSource' },
  { label: '新建组', key: 'createGroup' },
]

const modal = useModal()

function handleSelect(key: string) {
  const viewUiStore = useViewUiStore()

  switch (key) {
    case 'addSource':
      viewUiStore.openModal(modal, {
        preset: 'card',
        content: () => h(AddSource),
        closable: false,
        contentStyle: 'padding: 0;',
        style: 'width: auto; height: auto;',
      })
      break
    case 'createGroup':
      viewUiStore.openModal(modal, {
        preset: 'card',
        content: () => h(CreateSourceGroup),
        closable: false,
        contentStyle: 'padding: 0;',
        style: 'width: auto; height: auto;',
      })
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
