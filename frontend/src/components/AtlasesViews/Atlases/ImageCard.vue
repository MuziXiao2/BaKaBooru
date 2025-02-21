<script setup lang="ts">
import type Atlas from '@/types/Atlas'
import { toRefs } from 'vue'

const props = defineProps<{ atlas: Atlas }>()
const { atlas } = toRefs(props)
</script>

<template>
  <n-layout has-sider sider-placement="right">
    <n-layout-content
      :native-scrollbar="false"
      content-style="
        height: 95vh;
        display: flex;
        justify-content: center;
        align-items: center;"
    >
      <n-image :src="atlas.cover_url" width="100%" object-fit="contain" />
    </n-layout-content>

    <n-layout-sider
      content-style="padding: 20px;"
      show-trigger="bar"
      width="30%"
      :collapsed-width="0"
      bordered
    >
      <n-h1>图集</n-h1>
      <n-h2>信息</n-h2>
      标题: {{ atlas.title }}
      <br />
      创建者:{{ atlas.creator }}
      <br />
      更新日期:{{ atlas.updated_at }}
      <br />
      创建日期:{{ atlas.create_at }}
      <n-h2>图片</n-h2>
      序号 文件名 操作

      <n-h2>操作</n-h2>
    </n-layout-sider>
  </n-layout>
</template>

<style scoped></style>
