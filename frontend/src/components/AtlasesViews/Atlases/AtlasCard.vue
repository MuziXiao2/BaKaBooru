<script setup lang="ts">
import { toRefs } from 'vue'
import type Atlas from '@/types/Atlas'
import ClickMenu from '@/components/AtlasesViews/Atlases/ClickMenu.vue'

const props = defineProps<{ atlas: Atlas }>()
const { atlas } = toRefs(props)

const options = [
  { label: 'A', key: 'a' },
  { label: 'B', key: 'b' },
  { type: 'divider', key: 'd1' },
  { label: 'C', key: 'c' },
  { label: 'D', key: 'd' },
  {
    label: 'O1', key: 'o1', children: [
      { label: 'E', key: 'e' },
      { label: 'F', key: 'f' },
      {
        label: 'O2', key: 'o2', children: [
          { label: 'G', key: 'g' },
          { label: 'H', key: 'h' }
        ]
      }
    ]
  }
]

const emit = defineEmits(['click'])

const handleClick = () => {
  emit('click')
}

</script>

<template>

  <ClickMenu id="atlas" :options="options">
    <n-card
      footer-style="padding: 0; height:30px;"
      hover="hover"
      style=" border-radius: 10px"
      @click="handleClick"
    >
      <template #cover>
        <img id="cover" :src="atlas.cover_url" alt="img">
      </template>
      <template #footer>
        <div id="text"> {{ atlas.name }}</div>
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
  transform: scale(1.05);
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
}


</style>
