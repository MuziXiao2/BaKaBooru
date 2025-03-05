<script setup lang="ts"></script>

<template>
  <n-layout :native-scrollbar="false" has-sider sider-placement="right">
    <n-layout :native-scrollbar="false">
      <n-layout-header>
        <slot name="header" />
      </n-layout-header>
      <n-layout-content>
        <slot name="content" />
      </n-layout-content>
    </n-layout>
    <n-layout-sider>
      <slot name="sider" />
    </n-layout-sider>
  </n-layout>
</template>

<style scoped>
.n-layout {
  height: calc(100vh - 43px);
}

.n-layout-content {
  height: 100%;
}
</style>
