<template>
  <div class="top-bar">
    <span class="title">我的图床系统</span>
    <el-dropdown>
      <span class="username">{{ username }}</span>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script setup>
const username = 'admin'
const logout = () => {
  // 清理 token，跳转登录页
}
</script>

<style scoped>
.top-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100%;
}
.title {
  font-size: 18px;
  font-weight: bold;
}
</style>
