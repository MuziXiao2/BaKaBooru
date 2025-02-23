<template>
  <n-config-provider :theme="theme">
    <n-message-provider>
      <n-loading-bar-provider>
        <app-layout>
          <template #header>
            <top-navbar />
          </template>
          <template #default>
            <router-view />
            <loading-spin v-if="currentUiStore.isLoading" />
            <message-box v-if="globalUiStore.message.visible" :message="globalUiStore.message" />
          </template>
        </app-layout>
      </n-loading-bar-provider>
    </n-message-provider>
  </n-config-provider>
</template>

<script setup lang="ts">
import { NConfigProvider, NMessageProvider, NLoadingBarProvider } from 'naive-ui'
import { useViewUiStore, useGlobalUiStore } from '@/stores'
import AppLayout from '@/components/layout/AppLayout.vue'
import LoadingSpin from '@/components/common/LoadingSpin.vue'
import MessageBox from '@/components/common/MessageBox.vue'
import { computed, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { darkTheme } from 'naive-ui'
import TopNavbar from '@/components/app/TopNavbar.vue'

const route = useRoute()
const globalUiStore = useGlobalUiStore()

const currentUiStore = computed(() => {
  switch (route.name) {
    case 'View':
      return useViewUiStore()
    default:
      return useViewUiStore()
  }
})

const theme = computed(() => (globalUiStore.isDarkMode ? darkTheme : null))

onMounted(() => {

})
</script>
