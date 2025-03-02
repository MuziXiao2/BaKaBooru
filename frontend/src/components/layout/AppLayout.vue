<script setup lang="ts"></script>

<template>
  <n-layout>
    <n-layout-header bordered>
      <slot name="header" />
    </n-layout-header>
    <slot name="default" />
  </n-layout>
</template>

<style scoped></style>
