<template>
  <div class="info-panel">
    <slot></slot>
    <div class="info-actions">
      <slot name="actions"></slot>
    </div>
  </div>
</template>

<style scoped>
.info-panel {
  flex: 1;
  padding: 16px;
  padding-bottom: 80px;
  overflow-y: auto;
}

.info-actions {
  position: absolute;
  bottom: 24px;
  right: 24px;
  display: flex;
  gap: 12px;
}
</style>
