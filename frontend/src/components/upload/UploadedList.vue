<script setup lang="ts">
import { VueDraggable } from 'vue-draggable-plus'
import { useUploadStateStore } from '@/stores/modules/upload/upload-state.ts'
import { defineComponent, h, nextTick, ref } from 'vue'
import { NInput } from 'naive-ui'
import { storeToRefs } from 'pinia'

const uploadStateStore = useUploadStateStore()
const { uploadedListData: data } = storeToRefs(uploadStateStore)

const ShowOrEdit = defineComponent({
  props: {
    value: [String, Number],
    onUpdateValue: [Function, Array],
  },
  setup(props) {
    const isEdit = ref(false)
    const inputRef = ref(null)
    const inputValue = ref(props.value)

    function handleOnClick() {
      isEdit.value = true
      nextTick(() => {
        inputRef.value.focus()
      })
    }

    function handleChange() {
      props.onUpdateValue(inputValue.value)
      isEdit.value = false
    }

    return () =>
      h(
        'div',
        {
          style: 'min-height: 22px',
          onClick: handleOnClick,
        },
        isEdit.value
          ? h(NInput, {
              ref: inputRef,
              value: inputValue.value,
              onUpdateValue: (v) => {
                inputValue.value = v
              },
              onChange: handleChange,
              onBlur: handleChange,
            })
          : props.value,
      )
  },
})

const columns = [
  {
    type: 'selection',
  },
  {
    title: '图集标题',
    key: 'title',
    width: 150,
    render: (row) => {
      const index = data.value.findIndex((item) => item.key === row.key)
      return h(ShowOrEdit, {
        value: row.title,
        onUpdateValue: (v) => {
          data.value[index].title = v
        },
      })
    },
  },
  {
    type: 'expand',
    expandable: () => true,
    renderExpand: (row) => {
      return `${row.title} is a good guy.`
    },
  },
]
</script>

<template>
  <VueDraggable v-if="data.length > 0" v-model="data" :animation="150" target=".n-data-table-tbody">
    <n-data-table :columns="columns" :data="data" :bordered="false" />
  </VueDraggable>
  <n-data-table v-else :columns="columns" :bordered="false" />
</template>

<style scoped></style>
