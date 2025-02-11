<script setup lang="ts">


</script>

<template>
  <n-layout-sider
    :show-collapsed-content="false"
    :collapsed-width="10"
    :width="200"
    collapse-mode="transform"
    show-trigger="bar"
    collapsed-trigger-style="margin:10px"
    bordered
  >
    <n-anchor>
      <n-anchor-link title="Local Source" href="#">
        <n-anchor-link title="Test Source" href="#" />
      </n-anchor-link>
      <n-anchor-link title="Network Source" href="#">
      </n-anchor-link>
    </n-anchor>
  </n-layout-sider>
</template>

<style scoped>


</style>
