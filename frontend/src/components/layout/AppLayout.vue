<script setup lang="ts"></script>

<template>
  <n-layout>
    <n-layout-header bordered>
      <slot name="header" />
    </n-layout-header>
    <n-layout>
      <n-layout-content>
        <slot />
      </n-layout-content>
    </n-layout>
  </n-layout>
</template>

<style scoped></style>
