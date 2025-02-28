<template>
  <n-modal
    v-model:show="internalVisible"
    preset="dialog"
    :title="'Test'"
    :content="message.content"
    positive-text="确认"
    @positive-click="handleClose"
  />
</template>

<script setup lang="ts">
import { NModal } from 'naive-ui'
import { computed, defineProps } from 'vue'
import type { Message } from '@/types/global'

const props = defineProps<{
  message: Message
}>()

const emit = defineEmits<{
  (e: 'update:message', value: { visible: boolean; content: string; title?: string }): void
}>()

const internalVisible = computed({
  get: () => props.message.visible,
  set: (value) => {
    emit('update:message', { ...props.message, visible: value })
  },
})

const handleClose = () => {
  internalVisible.value = false
}
</script>

<style scoped></style>
