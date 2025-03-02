<script setup lang="ts">
import type Atlas from '@/types/atlas'
import { nextTick } from 'vue'
import { useViewUiStore } from '@/stores'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'
import { useModal } from 'naive-ui'
import ViewAtlas from '@/components/modal/ViewAtlas.vue'

const props = defineProps<{ atlas: Atlas }>()

const modal = useModal()
const viewUiStore = useViewUiStore()
const viewStateStore = useViewStateStore()

const handleClick = async () => {
  viewStateStore.setCurrentAtlas(props.atlas)
  await viewUiStore.openViewAtlas(modal,ViewAtlas)
}

const handleContextMenu = (e: MouseEvent) => {
  e.preventDefault()
  viewUiStore.closeContextMenu()
  nextTick().then(() => {
    viewUiStore.openContextMenu(e.clientX, e.clientY, props.atlas)
  })
}

const defaultCoverUrl = 'https://xiao2-test.oss-cn-guangzhou.aliyuncs.com/1.png'
</script>

<template>
  <n-card
    footer-style="padding: 0; height:30px;"
    hover="hover"
    @click="handleClick"
    @contextmenu="handleContextMenu"
  >
    <template #cover>
      <img id="cover" :src="atlas.cover_url || defaultCoverUrl" alt="img" />
    </template>
    <template #footer>
      <div id="text">{{ atlas.title }}</div>
    </template>
  </n-card>
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
