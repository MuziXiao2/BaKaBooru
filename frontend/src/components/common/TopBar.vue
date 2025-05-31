<template>
  <div class="top-bar">
    <div class="left">
      <el-breadcrumb :separator-icon="ArrowRight" class="breadcrumb">
        <el-breadcrumb-item :to="{ path: '/' }">BaKaBooru</el-breadcrumb-item>
        <el-breadcrumb-item>{{ route.meta.title }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="right">
      <el-button
        class="action-btn"
        :class="{ 'is-active': isDark }"
        :icon="isDark ? Sunny : Moon"
        @click="toggleDark"
      />
      <el-button
        class="action-btn"
        :class="{ 'is-active': isSettingPage }"
        :icon="Setting"
        @click="goToSetting"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ArrowRight, Setting, Moon, Sunny } from '@element-plus/icons-vue'
import { useRoute, useRouter } from 'vue-router'
import { ref, computed, onMounted } from 'vue'

const route = useRoute()
const router = useRouter()
const isDark = ref(false)

const isSettingPage = computed(() => route.name === 'setting')

const toggleDark = () => {
  isDark.value = !isDark.value
  if (isDark.value) {
    document.documentElement.classList.add('dark')
    document.body.setAttribute('class', 'dark')
  } else {
    document.documentElement.classList.remove('dark')
    document.body.removeAttribute('class')
  }
  localStorage.setItem('theme', isDark.value ? 'dark' : 'light')
}

onMounted(() => {
  // 检查本地存储的主题设置
  const savedTheme = localStorage.getItem('theme')
  // 检查系统偏好
  const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches

  if (savedTheme === 'dark' || (savedTheme === null && prefersDark)) {
    isDark.value = true
    document.documentElement.classList.add('dark')
    document.body.setAttribute('class', 'dark')
  }
})

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

.right {
  display: flex;
  align-items: center;
  gap: 8px;
}

.action-btn {
  width: 40px;
  height: 40px;
  padding: 8px;
  border-radius: 8px;
  transition: var(--hover-transition);
  color: var(--el-text-color-regular);
  background-color: transparent;
  border: 1px solid transparent;
}

.action-btn:hover {
  color: var(--primary-color);
  background-color: var(--el-bg-color-overlay);
  border-color: var(--el-border-color-light);
}

.action-btn.is-active {
  color: var(--primary-color);
  background-color: var(--el-bg-color-overlay);
  border-color: var(--primary-color);
}

.action-btn :deep(.el-icon) {
  font-size: 20px;
  width: 20px;
  height: 20px;
}

@media (max-width: 768px) {
  .action-btn {
    width: 36px;
    height: 36px;
    padding: 6px;
  }

  .action-btn :deep(.el-icon) {
    font-size: 18px;
    width: 18px;
    height: 18px;
  }
}
</style>
