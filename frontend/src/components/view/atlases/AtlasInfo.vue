<script setup lang="ts">
import { onMounted } from 'vue'
import ImageTable from '@/components/view/atlases/ImageTable.vue'
import { useViewUiStore } from '@/stores/modules/view.ts'

const viewUiStore = useViewUiStore()

onMounted(async () => {
  viewUiStore.startLoading()
  await viewUiStore.fetchImages()
  viewUiStore.stopLoading()
})
</script>

<template>
  <n-layout has-sider sider-placement="right" v-if="viewUiStore.isImagesLoaded">
    <n-layout-content
      :native-scrollbar="false"
      content-style="
          height:100%;
          display: flex;
          justify-content: center;
          align-items: center;
        "
    >
      <n-image :src="viewUiStore.currentImage.url" width="100%" object-fit="contain" />
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
          标题: {{ viewUiStore.currentAtlas.title }}
          <br />
          创建者:{{ viewUiStore.currentAtlas.creator }}
          <br />
          更新日期:{{ viewUiStore.currentAtlas.updated_at }}
          <br />
          创建日期:{{ viewUiStore.currentAtlas.create_at }}
        </n-collapse-item>
        <n-collapse-item title="图片" name="images">
          <ImageTable
            :columns="viewUiStore.table.columns"
            :data="viewUiStore.table.row"
            :row-props="
              (row) => {
                return {
                  style: 'cursor: pointer;',
                  onClick: () => viewUiStore.setCurrentImage(row.sn),
                }
              }
            "
          />
          <n-popconfirm positive-text="上传" negative-text="取消">
            <template #trigger>
              <n-button>上传</n-button>
            </template>
            <n-upload
              action="http://localhost:8080/api/images/upload"
              :headers="{
                'naive-info': 'hello!',
              }"
              :data="{
                'naive-data': 'cool! naive!',
              }"
            >
              <n-button>上传文件</n-button>
            </n-upload>
          </n-popconfirm>
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
  <n-spin v-else />
</template>

<style scoped></style>
