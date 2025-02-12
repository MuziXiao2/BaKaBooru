<script setup lang="ts">
import SourceList from '@/components/AtlasesViews/Sider/SourceList.vue'
import SearchBar from '@/components/AtlasesViews/Sider/SearchBar.vue'
</script>

<template>
  <n-card
    id="sider"
    size="small"
    content-style="padding: 5px;"
  >
    <!--图源列表-->
    <SourceList />
    <hr>
    <!--搜索栏-->
    <SearchBar />
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
