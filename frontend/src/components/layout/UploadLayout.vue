<script setup lang="ts"></script>

<template>
  <n-layout
    style="height: calc(100vh - 43px)"
    content-style="height:100%;display: flex; flex-direction: column"
    :native-scrollbar="false"
    sider-placement="right"
  >
    <n-layout-header>
      <slot name="header" />
    </n-layout-header>

    <n-layout-content>
      <n-layout
        style="height: 100%"
        content-style="height:100%"
        :native-scrollbar="false"
        has-sider
      >
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
