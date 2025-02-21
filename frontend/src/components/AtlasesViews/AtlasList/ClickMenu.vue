<script setup lang="ts">

import { nextTick, ref } from 'vue'

const props = defineProps({
  options: Array
})

const showDropdown = ref(false)
const x = ref(0)
const y = ref(0)

const handleSelect = (key: string | number) => {
  showDropdown.value = false
}

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

<style scoped>


</style>
