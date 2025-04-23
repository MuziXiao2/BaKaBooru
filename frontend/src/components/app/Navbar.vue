<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router'
import { computed } from 'vue'

const route = useRoute()
const router = useRouter()
const activeNavItem = computed(() => route.meta.navItem)
const handleNavChange = (key: string) => {
  router.push('/' + key)
}

const options = [
  { label: '浏览', key: 'view' },
  { label: '上传', key: 'upload' },
  { label: '标签', key: 'tag' },
  { label: '设置', key: 'setting' },
]
</script>

<template>
  <n-menu
    mode="horizontal"
    :value="activeNavItem"
    :options="options"
    @update:value="handleNavChange"
  />
</template>

<style scoped></style>
