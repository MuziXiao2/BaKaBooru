import { ref, computed, onMounted, onUnmounted, type Ref } from 'vue'
import type { IVirtualWaterFallProps, IDataItem, IRenderItem } from './types'
import {  rafThrottle } from './utils'

export function useWaterFall(
  props: IVirtualWaterFallProps,
  emit: any,
  contentRef: Ref<HTMLDivElement | null>,
) {
  const list = ref<IDataItem[]>([])
  const renderList = ref<IRenderItem[]>([])
  const total = ref(0)
  const page = ref(1)
  const loading = ref(false)
  const isEnd = ref(false)

  const columnHeights = ref<number[]>([])
  const gap = computed(() => props.gap || 20)

  const contentStyle = computed(() => ({
    height: Math.max(...columnHeights.value, 0) + 'px',
  }))

  const fetchData = async () => {
    if (loading.value || isEnd.value) return
    loading.value = true
    const res = await props.request(page.value, props.requestSize || props.columnItemCount || 20)
    list.value.push(...res.list)
    total.value = res.total
    if (list.value.length >= total.value) isEnd.value = true
    renderItems()
    emit('finishGetList')
    page.value += 1
    loading.value = false
  }

  const renderItems = () => {
    const cols = props.column || 4
    Array.from({ length: cols }, () => [])
    columnHeights.value = new Array(cols).fill(0)
    const tempRenderList: IRenderItem[] = []

    for (let i = 0; i < list.value.length; i++) {
      const item = list.value[i]
      const scale = item.height / item.width
      const colWidth = contentRef.value?.clientWidth
        ? (contentRef.value.clientWidth - (cols - 1) * gap.value) / cols
        : 200

      const h = colWidth * scale
      const minIndex = columnHeights.value.indexOf(Math.min(...columnHeights.value))
      const x = (colWidth + gap.value) * minIndex
      const y = columnHeights.value[minIndex]

      columnHeights.value[minIndex] += h + gap.value

      tempRenderList.push({
        item,
        h,
        y,
        style: {
          width: `${colWidth}px`,
          height: `${h}px`,
          transform: `translate(${x}px, ${y}px)`,
        },
      })
    }

    renderList.value = tempRenderList
  }

  const onScroll = rafThrottle(() => {
    const el = contentRef.value
    if (!el) return
    const scrollBottom = el.scrollTop + el.clientHeight
    if (scrollBottom >= el.scrollHeight - 100) {
      fetchData()
    }
  })

  onMounted(() => {
    fetchData()
    contentRef.value?.addEventListener('scroll', onScroll)
  })

  onUnmounted(() => {
    contentRef.value?.removeEventListener('scroll', onScroll)
  })

  return {
    renderList,
    contentStyle,
  }
}
