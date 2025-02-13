<script setup lang="ts">
import { useSiderStore } from '@/stores/siderStore'
import { useSourceStore } from '@/stores/sourceStore.ts'
import { onMounted, ref } from 'vue'

const sourceStore = useSourceStore()
const siderStore = useSiderStore()

// 创建一个变量来标记图源是否加载完成
const isSourcesLoaded = ref(false)

// 保证图源获取完成后再获取侧边栏选项
onMounted(async () => {
  // 等待获取图源
  await sourceStore.fetchSources()

  // 图源获取完成后，再更新状态并获取侧边栏选项
  isSourcesLoaded.value = true
  console.log('[BaKaBooru] 获取选项成功', siderStore.options)
})

// 处理菜单选择事件
const handleMenuSelect = (key: string) => {
  // 处理菜单选中逻辑
  siderStore.setSelectedSourceId(+key)
  console.log('[BaKaBooru] 当前选中图源id:', key)
}
</script>

<template>
  <n-card
    id="source-list"
    size="small"
    title="图源"
  >
    <!-- 只有在图源加载完成后，才渲染菜单 -->
    <n-menu
      v-if="isSourcesLoaded"
      :options="siderStore.options"
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
