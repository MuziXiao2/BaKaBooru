<template>
  <div class="top-bar">
    <div class="left">
      <el-breadcrumb :separator-icon="ArrowRight" class="breadcrumb">
        <el-breadcrumb-item :to="{ path: '/' }">BaKaBooru</el-breadcrumb-item>
        <el-breadcrumb-item>{{ route.meta.title }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-button :icon="Setting" text size="large" class="setting-btn" @click="goToSetting" />
  </div>
</template>

<script setup>
import { ArrowRight, Setting } from '@element-plus/icons-vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

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
  height: 5vh;
  padding: 0 16px;
  border-bottom: 1px solid #eee;
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
