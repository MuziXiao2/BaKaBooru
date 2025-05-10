<script setup lang="ts">
import HomeLayout from '@/components/HomeLayout.vue'
import LeftMenu from '@/components/left-sider/LeftMenu.vue'
import AtlasList from '@/components/content/AtlasList.vue'
import HomePage from '@/components/content/HomePage.vue'
import { useStateStore, useSoucreStore } from '@/stores'
import { onMounted } from 'vue'
import LoadingSpin from '@/components/common/LoadingSpin.vue'

const stateStore = useStateStore()
const soucreStore = useSoucreStore()

onMounted(async () => await soucreStore.update())
</script>

<template>
  <HomeLayout>
    <template #header>
      <n-breadcrumb>
        <n-breadcrumb-item>主页</n-breadcrumb-item>
        <n-breadcrumb-item>B</n-breadcrumb-item>
      </n-breadcrumb>
    </template>
    <template #left-sider>
      <left-menu />
    </template>
    <template #content>
      <HomePage v-if="stateStore.selectedKey === 'home'" />
      <AtlasList v-else-if="soucreStore.isSourcesLoaded" />
      <LoadingSpin v-else />
    </template>
  </HomeLayout>
</template>

<style scoped></style>
