
<script setup lang="ts">
import { ref, nextTick } from 'vue'

const options = [
  { label: '杰·盖茨比', key: 'jay gatsby' },
  { label: '黛西·布坎南', key: 'daisy buchanan' },
  { type: 'divider', key: 'd1' },
  { label: '尼克·卡拉威', key: 'nick carraway' },
  {
    label: '其他', key: 'others1', children: [
      { label: '乔丹·贝克', key: 'jordan baker' },
      { label: '汤姆·布坎南', key: 'tom buchanan' },
      {
        label: '其他', key: 'others2', children: [
          { label: '鸡肉', key: 'chicken' },
          { label: '牛肉', key: 'beef' }
        ]
      }
    ]
  }
]

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

const onClickoutside = () => {
  showDropdown.value = false
}

</script>

<template>
  <div @contextmenu="handleContextMenu">右击</div>
  <n-dropdown
    placement="bottom-start"
    trigger="manual"
    :x="x"
    :y="y"
    :options="options"
    :show="showDropdown"
    :on-clickoutside="onClickoutside"
    @select="handleSelect"
  >
  </n-dropdown>
</template>


<style scoped>

div {
  height: 100%;
  background-color: rgba(0, 128, 0, 0.5)
}

</style>
