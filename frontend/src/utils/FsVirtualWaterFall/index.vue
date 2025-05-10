<template>
  <div class="simple-waterfall" ref="containerRef">
    <div class="simple-waterfall-list" :style="{ height: `${maxHeight}px` }">
      <div
        class="simple-waterfall-item"
        v-for="{ item, style } in renderList"
        :key="item.id"
        :style="style"
      >
        <slot :item="item" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, watchEffect, reactive, onMounted } from 'vue'

interface ImageItem {
  uuid: string
  width: number
  height: number
  [key: string]: any
}

defineProps<{
  items: ImageItem[]
  column?: number
  gap?: number
}>()

const props = withDefaults(defineProps(), {
  column: 4,
  gap: 16,
})

const containerRef = ref<HTMLDivElement | null>(null)

const state = reactive({
  renderList: [] as Array<{ item: ImageItem; style: any }>,
  maxHeight: 0,
})

const calculateLayout = () => {
  const containerWidth = containerRef.value?.clientWidth || 0
  const columnHeights = Array(props.column).fill(0)
  const columnWidth = (containerWidth - (props.column - 1) * props.gap) / props.column

  const layout: typeof state.renderList = []

  props.items.forEach((item) => {
    const height = Math.floor((item.height / item.width) * columnWidth)
    const minCol = columnHeights.indexOf(Math.min(...columnHeights))
    const x = minCol * (columnWidth + props.gap)
    const y = columnHeights[minCol]

    layout.push({
      item,
      style: {
        position: 'absolute',
        width: `${columnWidth}px`,
        height: `${height}px`,
        transform: `translate(${x}px, ${y}px)`,
      },
    })

    columnHeights[minCol] += height + props.gap
  })

  state.renderList = layout
  state.maxHeight = Math.max(...columnHeights)
}

onMounted(() => {
  new ResizeObserver(calculateLayout).observe(containerRef.value!)
})

watchEffect(calculateLayout)

const renderList = state.renderList
const maxHeight = state.maxHeight
</script>

<style scoped>
.simple-waterfall {
  width: 100%;
  position: relative;
}
.simple-waterfall-list {
  position: relative;
  width: 100%;
}
.simple-waterfall-item {
  position: absolute;
  transition: all 0.3s;
  box-sizing: border-box;
}
</style>
