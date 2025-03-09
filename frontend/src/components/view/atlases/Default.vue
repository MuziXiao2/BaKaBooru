<template>
  <n-tree
    block-line
    checkable
    draggable
    :data="data"
    :checked-keys="checkedKeys"
    :expanded-keys="expandedKeys"
    :render-switcher-icon="renderSwitcherIcon"
    @drop="handleDrop"
    @update:checked-keys="handleCheckedKeysChange"
    @update:expanded-keys="handleExpandedKeysChange"
  />
</template>

<script setup lang="ts">
import type { TreeDropInfo, TreeOption, TreeRenderFns } from 'naive-ui'
import { ref, h } from 'vue'
import { NIcon } from 'naive-ui'
import { ChevronForwardOutline, ChevronDownOutline } from '@vicons/ionicons5'

// 定义节点类型
interface CustomTreeOption extends TreeOption {
  type: 'Atlas' | 'Image'
}

// 数据生成函数
function createData(): CustomTreeOption[] {
  return [
    {
      label: 'Atlas 1',
      key: 'atlas1',
      type: 'Atlas',
      children: [
        { label: 'Image 1-1', key: 'image1-1', type: 'Image', children: [] },
        { label: 'Image 1-2', key: 'image1-2', type: 'Image', children: [] },
      ],
    },
    { label: 'Atlas 2', key: 'atlas2', type: 'Atlas', children: [] },
    { label: 'Image 3', key: 'image3', type: 'Image', children: [] },
    { label: 'Image 4', key: 'image4', type: 'Image', children: [] },
  ]
}

// 查找兄弟节点和索引
function findSiblingsAndIndex(
  node: CustomTreeOption,
  nodes: CustomTreeOption[],
): [CustomTreeOption[], number] | [null, null] {
  if (!nodes) return [null, null]
  for (let i = 0; i < nodes.length; ++i) {
    if (nodes[i].key === node.key) return [nodes, i]
    const [siblings, index] = findSiblingsAndIndex(node, nodes[i].children as CustomTreeOption[])
    if (siblings && index !== null) return [siblings, index]
  }
  return [null, null]
}

// 判断节点是否在第一层（没有父节点）
function isTopLevel(node: CustomTreeOption, topNodes: CustomTreeOption[]): boolean {
  return topNodes.some((topNode) => topNode.key === node.key)
}

// 自定义展开图标渲染
const renderSwitcherIcon: TreeRenderFns['renderSwitcherIcon'] = ({ option, expanded }) => {
  const typedOption = option as CustomTreeOption
  if (typedOption.type === 'Image') {
    return null // 隐藏 Image 的展开图标
  }
  if (typedOption.type === 'Atlas') {
    // 为 Atlas 渲染展开/收起图标
    return h(NIcon, null, {
      default: () => h(ChevronForwardOutline),
    })
  }
  return null
}

// 响应式状态
const expandedKeys = ref<string[]>([])
const checkedKeys = ref<string[]>([])
const data = ref(createData())

// 事件处理函数
const handleExpandedKeysChange = (keys: string[]) => {
  expandedKeys.value = keys
}

const handleCheckedKeysChange = (keys: string[]) => {
  checkedKeys.value = keys
}

const handleDrop = ({ node, dragNode, dropPosition }: TreeDropInfo) => {
  const typedNode = node as CustomTreeOption
  const typedDragNode = dragNode as CustomTreeOption
  const [dragNodeSiblings, dragNodeIndex] = findSiblingsAndIndex(typedDragNode, data.value)
  if (dragNodeSiblings === null || dragNodeIndex === null) return

  console.log(
    `Dragging ${typedDragNode.label} (type: ${typedDragNode.type}) to ${typedNode.label} (type: ${typedNode.type}), dropPosition: ${dropPosition}`,
  )

  // 先移除拖动的节点
  dragNodeSiblings.splice(dragNodeIndex, 1)

  // 自定义拖拽逻辑
  if (dropPosition === 'inside') {
    // Image 拖到第一层 Image 上，创建新的 Atlas
    if (
      typedDragNode.type === 'Image' &&
      typedNode.type === 'Image' &&
      isTopLevel(typedNode, data.value)
    ) {
      const [nodeSiblings, nodeIndex] = findSiblingsAndIndex(typedNode, data.value)
      if (nodeSiblings === null || nodeIndex === null) return

      delete typedNode.children
      delete typedDragNode.children

      const newAtlas: CustomTreeOption = {
        label: `New Atlas (${typedNode.label} + ${typedDragNode.label})`,
        key: `atlas-${Date.now()}`,
        type: 'Atlas',
        children: [typedNode, typedDragNode],
      }
      nodeSiblings.splice(nodeIndex, 1, newAtlas)
      console.log(
        `Merged ${typedDragNode.label} into ${typedNode.label} to create ${newAtlas.label}`,
      )
    }
    // Image 拖到 Atlas 上，放入其子节点
    else if (typedNode.type === 'Atlas' && typedDragNode.type === 'Image') {
      if (typedNode.children) {
        typedNode.children.unshift(typedDragNode)
      } else {
        typedNode.children = [typedDragNode]
      }
      console.log(`Added ${typedDragNode.label} to ${typedNode.label}`)
    }
    // Atlas 拖到第一层 Atlas 上，合并其内部 Image
    else if (
      typedDragNode.type === 'Atlas' &&
      typedNode.type === 'Atlas' &&
      isTopLevel(typedNode, data.value)
    ) {
      if (typedDragNode.children && typedDragNode.children.length > 0) {
        if (typedNode.children) {
          typedNode.children.unshift(...typedDragNode.children)
        } else {
          typedNode.children = [...typedDragNode.children]
        }
        console.log(`Merged Images from ${typedDragNode.label} into ${typedNode.label}`)
      } else {
        console.log(`No Images in ${typedDragNode.label} to merge into ${typedNode.label}`)
      }
    } else {
      if (typedNode.type === 'Image' && !isTopLevel(typedNode, data.value)) {
        console.log('Invalid drop: Image can only be merged with a top-level Image')
      } else if (typedDragNode.type === 'Atlas' && !isTopLevel(typedNode, data.value)) {
        console.log("Invalid drop: Atlas cannot be dropped inside another Atlas's children")
      } else {
        console.log(
          'Invalid drop: Only Image or top-level Atlas can be dropped inside Atlas or first-level Image',
        )
      }
      dragNodeSiblings.splice(dragNodeIndex, 0, typedDragNode)
    }
  } else if (dropPosition === 'before' || dropPosition === 'after') {
    const [nodeSiblings, nodeIndex] = findSiblingsAndIndex(typedNode, data.value)
    if (nodeSiblings === null || nodeIndex === null) return

    if (typedDragNode.type === 'Atlas' && typedNode.type === 'Atlas') {
      console.log('Invalid drop: Atlas cannot be dropped before or after another Atlas')
      dragNodeSiblings.splice(dragNodeIndex, 0, typedDragNode)
    } else if (nodeSiblings[0]?.type === 'Atlas' || nodeSiblings[0]?.type === 'Image') {
      const insertIndex = dropPosition === 'before' ? nodeIndex : nodeIndex + 1
      nodeSiblings.splice(insertIndex, 0, typedDragNode)
      console.log(`Moved ${typedDragNode.label} to ${dropPosition} ${typedNode.label}`)
    } else {
      console.log('Invalid drop: Atlas can only be dropped at the top level')
      dragNodeSiblings.splice(dragNodeIndex, 0, typedDragNode)
    }
  }

  data.value = Array.from(data.value) // 触发响应式更新
}
</script>
