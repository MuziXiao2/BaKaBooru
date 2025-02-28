<script setup lang="ts">
import ViewLayout from '@/components/layout/ViewLayout.vue'
import Sider from '@/components/view/Sider.vue'
import Atlases from '@/components/view/Atlases.vue'
import AtlasInfo from '@/components/view/AtlasInfo.vue'
</script>

<template>
  <view-layout>
    <template #sider>
      <sider />
    </template>
    <template #content>
      <atlases />
    </template>
    <template #modal>
      <atlas-info />
    </template>
  </view-layout>
</template>

<style scoped></style>
