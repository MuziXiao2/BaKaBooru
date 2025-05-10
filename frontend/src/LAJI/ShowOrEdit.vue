<script setup lang="ts">
import { ref, nextTick, defineProps } from 'vue'
import { NInput } from 'naive-ui'

// 定义 props
const props = defineProps<{
  value: string
  onUpdateValue: (value: string) => void
}>()

// 响应式状态
const isEdit = ref(false)
const inputRef = ref<InstanceType<typeof NInput> | null>(null)
const inputValue = ref(props.value)

// 点击切换到编辑模式
function handleOnClick() {
  isEdit.value = true
  nextTick(() => {
    inputRef.value?.focus()
  })
}

// 保存更改并退出编辑模式
function handleChange() {
  props.onUpdateValue(inputValue.value)
  isEdit.value = false
}
</script>

<template>
  <div style="min-height: 22px" @click="handleOnClick">
    <n-input
      v-if="isEdit"
      ref="inputRef"
      v-model:value="inputValue"
      @change="handleChange"
      @blur="handleChange"
    />
    <span v-else>{{ props.value }}</span>
  </div>
</template>

<style scoped>
/* 可选：添加一些样式 */
</style>
