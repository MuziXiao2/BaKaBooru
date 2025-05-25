<template>
  <el-container>
    <el-aside class="side" width="250px">
      <slot name="side" />
    </el-aside>
    <el-container>
      <el-main class="main">
        <slot name="main" />
      </el-main>
      <el-footer class="footer">
        <slot name="footer" />
      </el-footer>
    </el-container>
  </el-container>
</template>

<script setup lang="ts"></script>

<style scoped>
.side {
  display: flex;
  border-right: 2px solid #ccc;
  background: #f2f2f2;
  flex-direction: column;
  width: 15vw;
}

.main {
  overflow: hidden;
  padding: 0;
  height: 90vh;
}

.footer {
  display: flex;
  height: 5vh;
  overflow: hidden;
  align-items: center;
  justify-content: center;
  border-top: 1px solid #ebeef5;
}

</style>
