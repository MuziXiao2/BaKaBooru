<template>
  <n-tree
    block-line
    checkable
    cascade
    draggable
    :data="data"
    :render-label="renderLabel"
    :checked-keys="checkedKeys"
    :expanded-keys="expandedKeys"
    :render-switcher-icon="renderSwitcherIcon"
    @drop="handleDrop"
    @update:checked-keys="handleCheckedKeysChange"
    @update:expanded-keys="handleExpandedKeysChange"
  />
</template>

<script setup lang="ts">
import type { TreeDropInfo, TreeOption } from 'naive-ui'
import { ref, h } from 'vue'
import { NIcon, NButton } from 'naive-ui'
import { ChevronForwardOutline } from '@vicons/ionicons5'
import { useUploadStateStore } from '@/stores/modules/upload/upload-state.ts'
import { storeToRefs } from 'pinia'
import type { CustomTreeOption } from '@/types/upload'

// Pinia store 数据
const { data } = storeToRefs(useUploadStateStore())

// 查找兄弟节点和索引
function findSiblingsAndIndex(
  node: CustomTreeOption,
  nodes: CustomTreeOption[],
): [CustomTreeOption[], number] | [null, null] {
  if (!nodes) return [null, null]
  for (let i = 0; i < nodes.length; i++) {
    if (nodes[i].key === node.key) return [nodes, i]
    const [siblings, index] = findSiblingsAndIndex(node, nodes[i].children as CustomTreeOption[])
    if (siblings && index !== null) return [siblings, index]
  }
  return [null, null]
}

// 判断是否为第一层节点
function isTopLevel(node: CustomTreeOption, topNodes: CustomTreeOption[]): boolean {
  return topNodes.some((topNode) => topNode.key === node.key)
}

// 自定义展开图标
const renderSwitcherIcon = ({ option }: { option: TreeOption }) => {
  const typedOption = option as CustomTreeOption
  return typedOption.type === 'Atlas'
    ? h(NIcon, null, { default: () => h(ChevronForwardOutline) })
    : null
}

// 自定义标签渲染
const renderLabel = ({ option }: { option: TreeOption }) =>
  h(NButton, { text: true, type: 'primary' }, { default: () => option.label })

// 响应式状态
const expandedKeys = ref<string[]>([])
const checkedKeys = ref<string[]>([])

// 事件处理
const handleExpandedKeysChange = (keys: string[]) => {
  expandedKeys.value = keys
}

const handleCheckedKeysChange = (keys: string[]) => {
  checkedKeys.value = keys
}

const handleDrop = ({ node, dragNode, dropPosition }: TreeDropInfo) => {
  const typedNode = node as CustomTreeOption
  const typedDragNode = dragNode as CustomTreeOption
  const [dragSiblings, dragIndex] = findSiblingsAndIndex(typedDragNode, data.value)
  if (!dragSiblings || dragIndex === null) return

  dragSiblings.splice(dragIndex, 1) // 移除拖动节点

  if (dropPosition === 'inside') {
    // Image 拖到第一层 Image，创建新 Atlas
    if (
      typedDragNode.type === 'Image' &&
      typedNode.type === 'Image' &&
      isTopLevel(typedNode, data.value)
    ) {
      const [nodeSiblings, nodeIndex] = findSiblingsAndIndex(typedNode, data.value)
      if (!nodeSiblings || nodeIndex === null) return

      const newAtlas: CustomTreeOption = {
        label: `New Atlas (${typedNode.label} + ${typedDragNode.label})`,
        key: `atlas-${Date.now()}`,
        type: 'Atlas',
        children: [typedNode, typedDragNode],
      }
      nodeSiblings.splice(nodeIndex, 1, newAtlas)
    }
    // Image 拖到 Atlas，添加为子节点
    else if (typedNode.type === 'Atlas' && typedDragNode.type === 'Image') {
      typedNode.children = typedNode.children || []
      typedNode.children.unshift(typedDragNode)
    }
    // Atlas 拖到第一层 Atlas，合并子节点
    else if (
      typedDragNode.type === 'Atlas' &&
      typedNode.type === 'Atlas' &&
      isTopLevel(typedNode, data.value)
    ) {
      if (typedDragNode.children?.length) {
        typedNode.children = typedNode.children || []
        typedNode.children.unshift(...typedDragNode.children)
      }
    } else {
      dragSiblings.splice(dragIndex, 0, typedDragNode) // 无效拖放，恢复原位
    }
  } else if (dropPosition === 'before' || dropPosition === 'after') {
    const [nodeSiblings, nodeIndex] = findSiblingsAndIndex(typedNode, data.value)
    if (!nodeSiblings || nodeIndex === null) return

    if (typedDragNode.type === 'Atlas' && typedNode.type === 'Atlas') {
      dragSiblings.splice(dragIndex, 0, typedDragNode) // Atlas 间不允许调整顺序
    } else if (nodeSiblings[0]?.type === 'Atlas' || nodeSiblings[0]?.type === 'Image') {
      const insertIndex = dropPosition === 'before' ? nodeIndex : nodeIndex + 1
      nodeSiblings.splice(insertIndex, 0, typedDragNode)
    } else {
      dragSiblings.splice(dragIndex, 0, typedDragNode) // 无效拖放，恢复原位
    }
  }

  data.value = Array.from(data.value) // 触发更新
}
</script>
