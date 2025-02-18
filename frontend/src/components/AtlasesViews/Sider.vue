<script setup lang="ts">
import Search from '@/components/AtlasesViews/Sider/Search.vue'
import Options from '@/components/AtlasesViews/Sider/Options.vue'
</script>

<template>
  <n-card
    id="sider"
    size="small"
    content-style="padding: 5px;"
  >
    <!--选项列表-->
    <Options />
    <hr>
    <!--搜索栏-->
    <Search />

  </n-card>
</template>

<style scoped>
#sider {
  padding: 0;
  margin: 5px;
  width: 95%;
  border: 2px solid gray;
  border-radius: 10px;
}


</style>
