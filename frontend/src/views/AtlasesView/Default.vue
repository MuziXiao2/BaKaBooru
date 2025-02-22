<script setup lang="ts"></script>

<template>
  <n-upload multiple directory-dnd action="http://localhost:8080/api/images/upload" :max="5">
    <n-upload-dragger>

    </n-upload-dragger>
  </n-upload>
</template>
<style scoped></style>
