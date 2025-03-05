<script setup lang="ts"></script>

<template>
  <n-layout style="height: calc(100vh - 43px)" :native-scrollbar="false" sider-placement="right">
    <n-layout-header>
      <slot name="header" />
    </n-layout-header>

    <n-layout-content>
      <n-layout :native-scrollbar="false" has-sider>
        <n-layout-content>
          <slot name="content" />
        </n-layout-content>
        <n-layout-sider>
          <slot name="sider" />
        </n-layout-sider>
      </n-layout>
    </n-layout-content>
  </n-layout>
</template>

<style scoped></style>
