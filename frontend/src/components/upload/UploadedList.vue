<script setup lang="ts">
import type { TreeDropInfo, TreeOption } from 'naive-ui'
import { h } from 'vue'
import { NButton } from 'naive-ui'
import { useUploadStateStore } from '@/stores/modules/upload/upload-state.ts'
import { storeToRefs } from 'pinia'
import type { CustomTreeOption } from '@/types/upload'

// 状态数据
const { data, expandedKeys, checkedKeys } = storeToRefs(useUploadStateStore())

// 查找兄弟节点和索引
function findSiblingsAndIndex(
  node: CustomTreeOption,
  nodes: Array<AtlasTreeOption>,
): [CustomTreeOption[], number] | [null, null] {
  if (!nodes) return [null, null]
  for (let i = 0; i < nodes.length; i++) {
    if (nodes[i].key === node.key) return [nodes, i]
    const [siblings, index] = findSiblingsAndIndex(
      node,
      nodes[i].children as Array<CustomTreeOption>,
    )
    if (siblings && index !== null) return [siblings, index]
  }
  return [null, null]
}

// 自定义标签渲染
const renderLabel = ({ option }: { option: TreeOption }) =>
  h(NButton, { text: true, type: 'primary' }, { default: () => option.title })

// 处理拖拽事件
const handleDrop = ({ node, dragNode, dropPosition }: TreeDropInfo) => {
  const typedNode = node as CustomTreeOption
  const typedDragNode = dragNode as CustomTreeOption

  const [dragSiblings, dragIndex] = findSiblingsAndIndex(typedDragNode, data.value)
  if (!dragSiblings || dragIndex === null) return

  // 先移除拖动节点
  dragSiblings.splice(dragIndex, 1)

  if (dropPosition === 'inside') {
    if (typedDragNode.type === 'Image' && typedNode.type === 'Atlas') {
      typedNode.children.unshift(typedDragNode)
    } else if (typedDragNode.type === 'Atlas' && typedNode.type === 'Atlas') {
      typedNode.children.unshift(...typedDragNode.children)
    } else {
      dragSiblings.splice(dragIndex, 0, typedDragNode) // 恢复原位
    }
  } else if (dropPosition === 'before' || dropPosition === 'after') {
    const [nodeSiblings, nodeIndex] = findSiblingsAndIndex(typedNode, data.value)
    if (!nodeSiblings || nodeIndex === null) {
      dragSiblings.splice(dragIndex, 0, typedDragNode)
      return
    }

    if (typedNode.type === 'Atlas' && typedDragNode.type === 'Atlas') {
      const insertIndex = dropPosition === 'before' ? nodeIndex : nodeIndex + 1
      nodeSiblings.splice(insertIndex, 0, typedDragNode)
    } else if (typedNode.type === 'Image' && typedDragNode.type === 'Image') {
      const insertIndex = dropPosition === 'before' ? nodeIndex : nodeIndex + 1
      nodeSiblings.splice(insertIndex, 0, typedDragNode)
    } else {
      dragSiblings.splice(dragIndex, 0, typedDragNode)
    }
  }

  // 使用深拷贝确保响应式更新
  data.value = JSON.parse(JSON.stringify(data.value))
}
</script>

<template>
  <n-tree
    label-field="title"
    block-line
    checkable
    cascade
    draggable
    :data="data"
    :render-label="renderLabel"
    @drop="handleDrop"
    v-model:expanded-keys="expandedKeys"
    v-model:checked-keys="checkedKeys"
  />
</template>

<style scoped></style>
