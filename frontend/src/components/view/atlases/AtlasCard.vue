<script setup lang="ts">
import { toRefs } from 'vue'
import type Atlas from '@/stores/types/atlas'
import ClickMenu from '@/components/view/atlases/ClickMenu.vue'

const props = defineProps<{ atlas: Atlas }>()
const { atlas } = toRefs(props)

const options = [
  { label: '查看', key: 'show' },
  { label: 'B', key: 'b' },
  { type: 'divider', key: 'd1' },
  { label: 'C', key: 'c' },
  { label: 'D', key: 'd' },
  {
    label: 'O1',
    key: 'o1',
    children: [
      { label: 'E', key: 'e' },
      { label: 'F', key: 'f' },
      {
        label: 'O2',
        key: 'o2',
        children: [
          { label: 'G', key: 'g' },
          { label: 'H', key: 'h' },
        ],
      },
    ],
  },
]

const emit = defineEmits(['click'])

const handleClick = () => {
  emit('click')
}

const defaultCoverUrl = 'https://xiao2-test.oss-cn-guangzhou.aliyuncs.com/1.png'
</script>

<template>
  <ClickMenu :options="options">
    <n-card
      footer-style="padding: 0; height:30px;"
      hover="hover"
      style="border-radius: 10px"
      @click="handleClick"
    >
      <template #cover>
        <img id="cover" :src="atlas.cover_url || defaultCoverUrl" alt="img" />
      </template>
      <template #footer>
        <div id="text">{{ atlas.title }}</div>
      </template>
    </n-card>
  </ClickMenu>
</template>

<style scoped>
.n-card {
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
