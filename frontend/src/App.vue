<template>
  <n-config-provider :theme="theme">
    <n-message-provider>
      <n-loading-bar-provider>
        <n-modal-provider>
          <app-layout>
            <template #header>
              <navbar />
            </template>
            <template #default>
              <router-view />
            </template>
          </app-layout>
          <message-box v-if="globalUiStore.message.visible" :message="globalUiStore.message" />
        </n-modal-provider>
      </n-loading-bar-provider>
    </n-message-provider>
  </n-config-provider>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { NConfigProvider, NMessageProvider, NLoadingBarProvider, darkTheme } from 'naive-ui'

import { useGlobalUiStore } from '@/stores'
import AppLayout from '@/components/layout/AppLayout.vue'
import MessageBox from '@/components/common/MessageBox.vue'
import Navbar from '@/components/app/Navbar.vue'

const globalUiStore = useGlobalUiStore()

const theme = computed(() => (globalUiStore.isDarkMode ? darkTheme : null))
</script>
