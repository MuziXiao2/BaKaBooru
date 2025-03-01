<script setup lang="ts">
import type Atlas from '@/types/atlas'
import ContextMenu from '@/components/view/atlases/atlas-card/ContextMenu.vue'

import { useViewUiStore } from '@/stores'

const props = defineProps<{ atlas: Atlas }>()

const viewUiStore = useViewUiStore()

const handleClick = async () => {
  viewUiStore.startLoading()

  await viewUiStore.openViewAtlas(props.atlas)

  viewUiStore.stopLoading()
}

const defaultCoverUrl = 'https://xiao2-test.oss-cn-guangzhou.aliyuncs.com/1.png'
</script>

<template>
  <ContextMenu :atlas="atlas">
    <n-card footer-style="padding: 0; height:30px;" hover="hover" @click="handleClick">
      <template #cover>
        <img id="cover" :src="atlas.cover_url || defaultCoverUrl" alt="img" />
      </template>
      <template #footer>
        <div id="text">{{ atlas.title }}</div>
      </template>
    </n-card>
  </ContextMenu>
</template>

<style scoped>
.n-card {
  border-radius: 10px;
  width: 200px;
  height: 230px;
}

.n-card:hover {
  transform: scale(1.02);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

#cover {
  width: 200px;
  height: 200px;
  object-fit: scale-down;
}

#text {
  font-size: 20px;
  color: gray;
  text-align: center;
}
</style>
