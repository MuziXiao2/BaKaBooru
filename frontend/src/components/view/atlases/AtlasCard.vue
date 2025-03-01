<script setup lang="ts">
import { toRefs } from 'vue'
import type Atlas from '@/types/atlas'
import ContextMenu from '@/components/view/atlases/atlas-card/ContextMenu.vue'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'
import { useViewUiStore } from '@/stores'
import { useImageStore } from '@/stores/common/image.ts'

const props = defineProps<{ atlas: Atlas }>()
const { atlas } = toRefs(props)

const imageStore = useImageStore()
const viewUiStore = useViewUiStore()
const viewStateStore = useViewStateStore()

const handleClick = async () => {
  viewUiStore.startLoading()

  viewStateStore.setCurrentAtlas(atlas.value)
  await imageStore.fetchImages()
  viewStateStore.setCurrentImage(imageStore.images[0])
  viewUiStore.openViewAtlas()

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
