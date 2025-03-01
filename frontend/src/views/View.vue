<script setup lang="ts">
import ViewLayout from '@/components/layout/ViewLayout.vue'
import Sider from '@/components/view/Sider.vue'
import Atlases from '@/components/view/Atlases.vue'
import ViewAtlas from '@/components/view/atlases/atlas-card/context-menu/ViewAtlas.vue'
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
      <view-atlas />
    </template>
  </view-layout>
</template>

<style scoped></style>
