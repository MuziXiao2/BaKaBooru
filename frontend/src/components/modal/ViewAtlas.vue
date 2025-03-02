<script setup lang="ts">
import ImageTable from '@/components/modal/view-atlas/ImageTable.vue'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'
import { storeToRefs } from 'pinia'
import { CloseOutline as CloseIcon } from '@vicons/ionicons5'
import { useViewUiStore } from '@/stores'

const viewUiStore = useViewUiStore()
const viewStateStore = useViewStateStore()
const { currentImage: image, currentAtlas: atlas } = storeToRefs(viewStateStore)
</script>

<template>
  <n-layout has-sider sider-placement="right">
    <n-layout-content
      :native-scrollbar="false"
      content-style="
          height:100%;
          display: flex;
          justify-content: center;
          align-items: center;
        "
    >
      <n-image v-if="image" :src="image.url" width="100%" object-fit="contain" />
      <n-empty v-else description="No Data" />
    </n-layout-content>

    <n-layout-sider
      content-style="padding: 20px;"
      show-trigger="bar"
      width="30%"
      :collapsed-width="0"
      bordered
      :show-collapsed-content="false"
    >
      <n-card
        :bordered="false"
        header-style="padding:0"
        content-style="padding:0"
        :segmented="{
          content: true,
          footer: 'soft',
        }"
        style="background: transparent"
      >
        <template #header>
          <n-h1>Atlas</n-h1>
        </template>

        <template #header-extra>
          <n-float-button :right="15" :top="15" shape="square" @click="viewUiStore.closeViewAtlas()">
            <CloseIcon />
          </n-float-button>
        </template>

        <template #default>
          标题:{{ atlas.title }}<br />
          创建者:{{ atlas.creator }}<br />
          更新日期:{{ atlas.updated_at }}<br />
          创建日期:{{ atlas.create_at }}

          <n-h1>Images</n-h1>
          <image-table />

          <n-h1>Tag</n-h1>
          test1
          <br />
          test2
          <br />
          test3
          <br />
          test4
        </template>

        <template #footer>
          <n-h1>Action</n-h1>
          <n-button>Test1</n-button>
          <n-button>Test2</n-button>
          <n-button>Test3</n-button>
        </template>
      </n-card>
    </n-layout-sider>
  </n-layout>
</template>

<style scoped>
.n-layout {
  height: 100vh;
}
</style>
