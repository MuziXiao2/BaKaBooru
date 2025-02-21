<script setup lang="ts">
import type Atlas from '@/types/Atlas'
import { ref, toRefs } from 'vue'
import ImageTable from '@/components/AtlasesViews/AtlasList/ImageTable.vue'
import { useImageStore } from '@/stores/imageStore.ts'

const imageStore = useImageStore()

const props = defineProps<{ atlas: Atlas }>()
const { atlas } = toRefs(props)

const data = ref([])
const columns = ref([
  { title: '序号', key: 'sn' },
  { title: '标题', key: 'title' },
  { title: '大小', key: 'size' },
])

const isImagesLoaded = ref(false)

// 加载图片
async function loadImages() {
  const atlas_id = atlas.value.id
  await imageStore.fetchImages(atlas_id)
  const images = imageStore.imagesMap[atlas_id]
  data.value = images.map((image) => ({
    sn: image.sn,
    title: image.title,
    size: '0kB',
  }))

  isImagesLoaded.value = true
}

onMounted(() => {
  loadImages()
})
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
      <n-image :src="atlas.cover_url" width="100%" object-fit="contain" />
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
          标题: {{ atlas.title }}
          <br />
          创建者:{{ atlas.creator }}
          <br />
          更新日期:{{ atlas.updated_at }}
          <br />
          创建日期:{{ atlas.create_at }}
        </n-collapse-item>
        <n-collapse-item title="图片" name="images">
          <ImageTable v-if="isImagesLoaded" :columns="columns" :data="data" />
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
