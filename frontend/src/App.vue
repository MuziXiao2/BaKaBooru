<script setup lang="ts">
import { computed } from 'vue'
import { NConfigProvider, NMessageProvider, NLoadingBarProvider, darkTheme } from 'naive-ui'

import { useGlobalUiStore } from '@/stores'
import MessageBox from '@/LAJI/MessageBox.vue'
import Home from '@/components/Home.vue'

const globalUiStore = useGlobalUiStore()
const theme = computed(() => (globalUiStore.isDarkMode ? darkTheme : null))
</script>

<template>
  <n-config-provider :theme="theme">
    <n-message-provider>
      <n-loading-bar-provider>
        <n-modal-provider>
          <Home />
          <message-box v-if="globalUiStore.message.visible" :message="globalUiStore.message" />
        </n-modal-provider>
      </n-loading-bar-provider>
    </n-message-provider>
  </n-config-provider>
</template>

<style scoped></style>
