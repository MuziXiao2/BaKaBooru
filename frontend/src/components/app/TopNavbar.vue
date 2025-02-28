<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router'
import { computed } from 'vue'
import type { MenuOption } from 'naive-ui'

const route = useRoute()
const router = useRouter()

const activeNavItem = computed(() => (route.meta.navItem as string) || 'view')

const navOptions: Array<MenuOption> = [{ label: '浏览', key: 'view' }]

const handleNavChange = (key: string) => {
  const routeMap: Record<string, string> = {
    view: '/view',
  }
  router.push(routeMap[key])
}
</script>

<template>
  <n-menu
    mode="horizontal"
    :value="activeNavItem"
    :options="navOptions"
    @update:value="handleNavChange"
  />
</template>

<style scoped></style>
