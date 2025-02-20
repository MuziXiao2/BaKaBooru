<script setup lang="ts">
import { ref } from 'vue'

const showModal = ref(false)


</script>
<template>
  <n-card @click="showModal = true">
    来吧
  </n-card>
  <n-modal v-model:show="showModal">
    <n-card
      style="width: 600px"
      title="模态框"
      :bordered="false"
      size="huge"
      role="dialog"
      aria-modal="true"
    >
      <template #header-extra>
        噢！
      </template>
      内容
      <template #footer>
        尾部
      </template>
    </n-card>
  </n-modal>
</template>

<style scoped>



</style>
