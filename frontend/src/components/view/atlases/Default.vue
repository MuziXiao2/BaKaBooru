<script setup lang="ts">
import { AddCircleOutline as AddIcon } from '@vicons/ionicons5'
</script>

<template>
  <n-card
    title="卡片分段示例"
    :segmented="{
      content: true,
      footer: 'soft',
    }"
  >
    <template #header-extra>
      <n-button text>
        <n-icon size="30">
          <AddIcon />
        </n-icon>
      </n-button>
    </template>
    卡片内容
    <template #footer> #footer</template>
    <template #action> #action</template>
  </n-card>
</template>
<style scoped></style>
