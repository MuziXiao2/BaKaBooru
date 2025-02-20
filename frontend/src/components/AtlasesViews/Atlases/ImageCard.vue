<script setup lang="ts">

const url = './images/2.png'

</script>

<template>
  <n-layout
    has-sider
    sider-placement="right"
  >
    <n-layout :native-scrollbar="false">
      <n-layout-content
        content-style="height:80vh"
        :native-scrollbar="false"
      >

        <n-image
          :src="url"
          width="100%"
          object-fit="contain"


        />

      </n-layout-content>
    </n-layout>
    <n-layout-sider
      content-style="padding: 20px;"
      show-trigger="bar"
      width="30%"
      :collapsed-width="0"
      bordered
    >
      TEST
    </n-layout-sider>
  </n-layout>

</template>

<style scoped>

</style>
