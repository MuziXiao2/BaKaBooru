<script setup lang="ts">
import type { MenuOption } from 'naive-ui'
import {
  Star as StarIcon
} from '@vicons/ionicons5'
import { renderIcon } from '@/utils/icon.ts'

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
