<script setup lang="ts">
import { AddCircleOutline as AddIcon } from '@vicons/ionicons5'
import type { DropdownOption } from 'naive-ui'

const options: DropdownOption[] = [
  { label: '添加图源', key: 'addSource' },
  { label: '新建组', key: 'createGroup' },
]

function handleSelect(option: DropdownOption) {
  console.log(option)
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
