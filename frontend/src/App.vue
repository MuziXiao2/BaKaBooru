<template>
  <n-config-provider :theme="theme">
    <n-message-provider>
      <n-loading-bar-provider>
        <app-layout>
          <template #header>
            <navbar />
          </template>
          <template #default>
            <router-view />
          </template>
        </app-layout>
        <loading-spin v-if="currentUiStore.isLoading" />
        <message-box v-if="globalUiStore.message.visible" :message="globalUiStore.message" />
      </n-loading-bar-provider>
    </n-message-provider>
  </n-config-provider>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import { NConfigProvider, NMessageProvider, NLoadingBarProvider, darkTheme } from 'naive-ui'

import { useViewUiStore, useGlobalUiStore } from '@/stores'
import AppLayout from '@/components/layout/AppLayout.vue'
import LoadingSpin from '@/components/common/LoadingSpin.vue'
import MessageBox from '@/components/common/MessageBox.vue'
import Navbar from '@/components/app/Navbar.vue'

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
</script>
