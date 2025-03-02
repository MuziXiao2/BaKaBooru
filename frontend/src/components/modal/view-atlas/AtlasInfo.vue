<script setup lang="ts">
import { storeToRefs } from 'pinia'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'

const atlas = storeToRefs(useViewStateStore()).currentAtlas
</script>

<template>

</template>

<style scoped></style>
