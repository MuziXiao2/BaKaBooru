<script setup lang="ts">
import type { MenuOption } from 'naive-ui'
import { h } from 'vue'
import {
  Star as StarIcon
} from '@vicons/ionicons5'
import type { Component } from 'vue'
import { NIcon } from 'naive-ui'

function renderIcon(icon: Component) {
  return () => h(NIcon, null, { default: () => h(icon) })
}

const menuOptions: MenuOption[] = [
  {
    label: 'BaKaBooru',
    key: 'title',
    icon: renderIcon(StarIcon)
  }
]
</script>

<template>
  <n-menu
    mode="horizontal"
    :options="menuOptions"
    responsive
  />
</template>

<style scoped>

</style>
