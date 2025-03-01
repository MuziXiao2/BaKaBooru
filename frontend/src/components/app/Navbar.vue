<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router'
import { computed } from 'vue'
import { useNavbarStore } from '@/stores/modules/navbar.ts'

const route = useRoute()
const router = useRouter()
const navbarStore = useNavbarStore()

const activeNavItem = computed(() => (route.meta.navItem as string) || 'view')

const handleNavChange = (key: string) => {
  router.push('/' + key)
}
</script>

<template>
  <n-menu
    mode="horizontal"
    :value="activeNavItem"
    :options="navbarStore.options"
    @update:value="handleNavChange"
  />
</template>

<style scoped></style>
