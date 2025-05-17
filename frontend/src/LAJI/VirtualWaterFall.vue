```vue
<template>
  <div class="fs-virtual-waterfall-container">
    <div class="fs-virtual-waterfall-content" ref="contentRef">
      <div class="fs-virtual-waterfall-list" :style="{ height: `${maxHeight}px` }">
        <div
          class="fs-virtual-waterfall-item"
          v-for="{ item, style } in renderList"
          :key="item.id"
          :style="style"
        >
          <slot name="item" :item="item"></slot>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, reactive, onMounted, onUnmounted } from 'vue'
import { debounce } from 'lodash-es'

// 类型定义
interface IDataItem {
  id: string | number // 唯一标识
  width: number // 原始宽度
  height: number // 原始高度
  [key: string]: any // 扩展属性
}

interface IRenderItem {
  item: IDataItem // 数据项
  y: number // 垂直位置
  h: number // 高度
  style: { width: string; height: string; transform: string } // 样式
}

interface IColumnQueue {
  list: IRenderItem[] // 列中的项
  height: number // 列高度
}

interface IVirtualWaterFallProps {
  items: IDataItem[] // 数据列表
  column?: number // 列数
  gap?: number // 间距
}

// 属性默认值
const props = withDefaults(defineProps<IVirtualWaterFallProps>(), {
  items: () => [],
  column: 4,
  gap: 20,
})

// 状态
const contentRef = ref<HTMLDivElement>()
const state = reactive({
  columns: Array(props.column)
    .fill(0)
    .map<IColumnQueue>(() => ({ list: [], height: 0 })), // 列队列
  viewWidth: 0, // 视口宽度
  viewHeight: 0, // 视口高度
  scrollTop: 0, // 滚动位置
  nextIndex: 0, // 下一个数据索引
})

// 计算属性
const itemWidth = computed(() => (state.viewWidth - (props.column - 1) * props.gap) / props.column) // 项宽度
const maxHeight = computed(() => Math.max(...state.columns.map((col) => col.height))) // 最大列高度
const renderList = computed(() =>
  state.columns
    .flatMap((col) => col.list)
    .filter(
      (item) => item.y + item.h > state.scrollTop && item.y < state.scrollTop + state.viewHeight,
    ),
) // 可见项

// 添加项到最短列
const addItems = (count = props.items.length) => {
  state.columns = Array(props.column)
    .fill(0)
    .map(() => ({ list: [], height: 0 }))
  state.nextIndex = 0
  for (let i = 0; i < count && state.nextIndex < props.items.length; i++) {
    const item = props.items[state.nextIndex]
    const colIndex = state.columns.reduce(
      (minIdx, col, idx) => (col.height < state.columns[minIdx].height ? idx : minIdx),
      0,
    )
    const col = state.columns[colIndex]
    const prevItem = col.list[col.list.length - 1]
    const height = (itemWidth.value * item.height) / item.width
    const y = prevItem ? prevItem.y + prevItem.h + props.gap : 0
    col.list.push({
      item,
      y,
      h: height,
      style: {
        width: `${itemWidth.value}px`,
        height: `${height}px`,
        transform: `translate(${colIndex * (itemWidth.value + props.gap)}px, ${y}px)`,
      },
    })
    col.height = y + height
    state.nextIndex++
  }
}

// 滚动处理
const handleScroll = debounce(() => {
  if (!contentRef.value) return
  state.scrollTop = contentRef.value.scrollTop
}, 100)

// 窗口大小变化
const handleResize = debounce(() => {
  if (!contentRef.value) return
  state.viewWidth = contentRef.value.clientWidth
  state.viewHeight = contentRef.value.clientHeight
  addItems(props.items.length)
}, 300)

// 初始化
const init = () => {
  if (!contentRef.value) return
  state.viewWidth = contentRef.value.clientWidth
  state.viewHeight = contentRef.value.clientHeight
  addItems(props.items.length)
  contentRef.value.addEventListener('scroll', handleScroll)
  window.addEventListener('resize', handleResize)
}

// 清理
const destroy = () => {
  contentRef.value?.removeEventListener('scroll', handleScroll)
  window.removeEventListener('resize', handleResize)
}

onMounted(init)
onUnmounted(destroy)
</script>

<style scoped lang="scss">
.fs-virtual-waterfall {
  &-container {
    width: 100%;
    height: 100%;
  }

  &-content {
    width: 100%;
    height: 100%;
    overflow-y: auto;
  }

  &-list {
    position: relative;
    width: 100%;
  }

  &-item {
    position: absolute;
    top: 0;
    left: 0;
    box-sizing: border-box;
  }
}
</style>
```
