<script setup lang="ts">
import { useSiderStore } from '@/stores/siderStore'

const siderStore = useSiderStore()
const options = siderStore.options

const handleMenuSelect = (key: string) => {
  sourceStore.setSelectedSource(key)
}

</script>
<template>
  <n-card
    id="source-list"
    size="small"
    title="图源"
  >
    <n-menu
      :options="options"
      :root-indent="0"
      :indent="10"
      @update:value="handleMenuSelect"
    >
    </n-menu>
  </n-card>
</template>

<style scoped>
#source-list {
  border: 1px solid gray;
  border-radius: 10px;
}


</style>
