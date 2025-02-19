<script setup lang="ts">
import { useSiderStore } from '@/stores/siderStore'
import { useSourceStore } from '@/stores/sourceStore.ts'
import { onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const sourceStore = useSourceStore()
const siderStore = useSiderStore()

const id = route.params.id === undefined ? 'default' : route.params.id

// 加载图源
const isSourcesLoaded = ref(false)
onMounted(async () => {
  await sourceStore.fetchSources()
  isSourcesLoaded.value = true
  console.log('[BaKaBooru] 获取选项成功', siderStore.options)
})

</script>

<template>
  <n-card
    id="source-list"
    size="small"
    content-style="padding: 5px;"
  >
    <n-menu
      v-if="isSourcesLoaded"
      :options="siderStore.options"
      :default-value="id"
      :root-indent="10"
      :indent="0"
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
