<script setup lang="ts">
import ImageTable from '@/components/view/atlases/atlas-card/context-menu/view-atlas/ImageTable.vue'
import { useViewStateStore } from '@/stores/modules/view/view-state.ts'

const viewStateStore = useViewStateStore()

const defaultImageUrl = 'https://xiao2-test.oss-cn-guangzhou.aliyuncs.com/1.png'
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
      <n-image
        :src="viewStateStore.currentImage?.url || defaultImageUrl"
        width="100%"
        object-fit="contain"
      />
    </n-layout-content>

    <n-layout-sider
      content-style="padding: 20px;"
      show-trigger="bar"
      width="30%"
      :collapsed-width="0"
      bordered
      :show-collapsed-content="false"
    >
      <n-h1>图集</n-h1>

      <n-collapse
        arrow-placement="right"
        :default-expanded-names="['info', 'images', 'tags', 'actions']"
      >
        <n-collapse-item title="信息" name="info">
          标题: {{ viewStateStore.currentAtlas?.title }}
          <br />
          创建者:{{ viewStateStore.currentAtlas?.creator }}
          <br />
          更新日期:{{ viewStateStore.currentAtlas?.updated_at }}
          <br />
          创建日期:{{ viewStateStore.currentAtlas?.create_at }}
        </n-collapse-item>
        <n-collapse-item title="图片" name="images">
          <ImageTable />
        </n-collapse-item>
        <n-collapse-item title="标签" name="tags">
          test1
          <br />
          test2
          <br />
          test3
          <br />
          test4
        </n-collapse-item>
        <n-collapse-item title="操作" name="actions">
          <n-button>Test1</n-button>
          <n-button>Test2</n-button>
          <n-button>Test3</n-button>
        </n-collapse-item>
      </n-collapse>
    </n-layout-sider>
  </n-layout>
</template>

<style scoped></style>
