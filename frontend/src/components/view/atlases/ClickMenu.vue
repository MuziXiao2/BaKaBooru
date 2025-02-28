<script setup lang="ts">
import { nextTick, ref } from 'vue'

const showDropdown = ref(false)
const x = ref(0)
const y = ref(0)

const handleSelect = (key: string | number) => {
  if (key === 'show') {
    console.log('666')
  }
  showDropdown.value = false
}

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

const handleContextMenu = (e: MouseEvent) => {
  e.preventDefault()
  showDropdown.value = false
  nextTick().then(() => {
    showDropdown.value = true
    x.value = e.clientX
    y.value = e.clientY
  })
}

const onClickOutside = () => {
  showDropdown.value = false
}
</script>

<template>
  <div @contextmenu="handleContextMenu">
    <slot />
  </div>
  <n-dropdown
    placement="bottom-start"
    trigger="manual"
    :x="x"
    :y="y"
    :options="options"
    :show="showDropdown"
    :on-clickoutside="onClickOutside"
    @select="handleSelect"
  />
</template>

<style scoped></style>
