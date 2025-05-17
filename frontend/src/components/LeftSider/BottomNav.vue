<template>
  <el-menu
    class="custom-menu"
    :default-active="globalUiStore.activePage"
    @select="handleSelect"
    text-color="#333"
    background-color="#f5f7fa"
    active-text-color="#409EFF"
  >
    <el-menu-item v-for="item in pageData" :key="item.label" :index="item.name">
      <el-icon class="menu-icon">
        <component :is="item.icon" />
      </el-icon>
      <span class="menu-label">{{ item.label }}</span>
    </el-menu-item>
  </el-menu>
</template>
<script setup lang="ts">
import { useRouter } from 'vue-router'
import { useGlobalUiStore } from '@/stores'
import { Headset, Picture, VideoCamera } from '@element-plus/icons-vue'

const globalUiStore = useGlobalUiStore()

const router = useRouter()
const handleSelect = (name: string) => {
  globalUiStore.activePage = name
  router.push({ name })
}

const pageData = [
  { label: '图片库', icon: Picture, path: '/gallery', name: 'Gallery' },
  { label: '视频库', icon: VideoCamera, path: '/videos', name: 'Videos' },
  { label: '音声库', icon: Headset, path: '/voices', name: 'Voices' },
]
</script>

<style scoped>
.custom-menu {
  width: 100%;
  border-right: none;
  padding: 12px;
}

.el-menu-item {
  display: flex;
  align-items: center;
  height: 48px;
  font-size: 18px;
  border-radius: 6px;
  transition: background-color 0.2s;
}

.el-menu-item:hover {
}

.menu-icon {
  margin-right: 12px;
}

.menu-label {
  font-weight: 500;
}
</style>
