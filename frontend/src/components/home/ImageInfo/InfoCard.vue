<template>
  <section class="info-card">
    <div class="header">
      <el-icon
        class="collapse-icon"
        :class="{ 'is-collapsed': isCollapsed }"
        @click="toggleCollapse"
      >
        <ArrowDown />
      </el-icon>
      <h3 v-if="title" class="title">{{ title }}</h3>
    </div>
    <div class="content" v-show="!isCollapsed">
      <slot></slot>
    </div>
  </section>
</template>

<script setup lang="ts">
import { ArrowDown } from '@element-plus/icons-vue'
import { ref } from 'vue'

defineProps<{
  title?: string
}>()

const isCollapsed = ref(false)

const toggleCollapse = () => {
  isCollapsed.value = !isCollapsed.value
}
</script>

<style scoped>
.info-card {
  padding: 16px;
  border-radius: 8px;
  background: var(--el-bg-color-overlay);
  margin-bottom: 16px;
  border: 1px solid var(--el-border-color-light);
  transition: var(--hover-transition);
}

.info-card:hover {
  border-color: var(--primary-color);
  box-shadow: var(--el-box-shadow);
}

.header {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 16px;
}

.collapse-icon {
  cursor: pointer;
  color: var(--el-text-color-secondary);
  font-size: 20px;
  transition: transform 0.3s ease;
}

.collapse-icon:hover {
  color: var(--primary-color);
}

.collapse-icon.is-collapsed {
  transform: rotate(-90deg);
}

.title {
  font-size: 18px;
  color: var(--el-text-color-primary);
  margin: 0;
  font-weight: 600;
  display: flex;
  align-items: center;
  gap: 8px;
}

.title::before {
  content: '';
  width: 4px;
  height: 16px;
  background-color: var(--primary-color);
  border-radius: 2px;
}

.content {
  padding: 0 8px;
}
</style>
