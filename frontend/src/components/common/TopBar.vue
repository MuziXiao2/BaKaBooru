<template>
  <div class="top-bar">
    <div class="left">
      <el-breadcrumb :separator-icon="ArrowRight" class="breadcrumb">
        <el-breadcrumb-item :to="{ path: '/' }">BaKaBooru</el-breadcrumb-item>
        <el-breadcrumb-item>{{ route.meta.title }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-button
      :icon="isDark ? Moon : Sunny"
      text
      size="large"
      class="setting-btn"
      @click="toggleDark"
    />
    <el-button :icon="Setting" text size="large" class="setting-btn" @click="goToSetting" />
  </div>
</template>

<script setup lang="ts">
import { ArrowRight, Moon, Setting, Sunny } from '@element-plus/icons-vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

import { useDark, useToggle } from '@vueuse/core'

const isDark = useDark()
const toggleDark = useToggle(isDark)

const goToSetting = () => {
  router.push({ name: 'setting' })
}
</script>

<style scoped>
.top-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 100%;
  padding: 0 16px;
}

.breadcrumb {
  font-size: 18px;
  font-weight: 500;
}

.setting-btn {
  font-size: 20px;
  padding: 8px;
}
</style>
