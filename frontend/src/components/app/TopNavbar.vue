<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router'
import { computed } from 'vue'
import { useTopNavbarStore } from '@/stores/modules/top-navbar.ts'

const route = useRoute()
const router = useRouter()
const topNavbarStore = useTopNavbarStore()

const activeNavItem = computed(() => (route.meta.navItem as string) || 'view')

const handleNavChange = (key: string) => {
  router.push('/' + key)
}
</script>

<template>
  <n-menu
    mode="horizontal"
    :value="activeNavItem"
    :options="topNavbarStore.options"
    @update:value="handleNavChange"
  />
</template>

<style scoped></style>
