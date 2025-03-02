<script setup lang="ts">
import ViewLayout from '@/components/layout/ViewLayout.vue'
import Sider from '@/components/view/Sider.vue'
import Atlases from '@/components/view/Atlases.vue'

</script>

<template>
  <view-layout>
    <template #sider>
      <sider />
    </template>
    <template #content>
      <atlases />
    </template>
  </view-layout>
</template>

<style scoped></style>
