<script setup lang="ts">
import type Atlas from '@/types/Atlas'
import { ref, toRefs, onMounted } from 'vue'
import ImageTable from '@/components/AtlasesViews/AtlasList/ImageTable.vue'
import { useImageStore } from '@/stores/imageStore.ts'
import type Image from '@/types/Image'

const imageStore = useImageStore()

const props = defineProps<{ atlas: Atlas }>()
const { atlas } = toRefs(props)

const images = ref<Image[]>([])

interface ImageRow {
  sn: number
  title: string
  size: string
}

const data = ref<ImageRow[]>([])
const columns = ref([
  { title: '序号', key: 'sn' },
  { title: '标题', key: 'title' },
  { title: '大小', key: 'size' },
])

const isImagesLoaded = ref(false)

const defaultUrl = 'https://xiao2-test.oss-cn-guangzhou.aliyuncs.com/1.png'
const imageUrl = ref(defaultUrl)

// 加载图片
async function loadImages() {
  const atlas_id = atlas.value.id
  await imageStore.fetchImages(atlas_id)
  images.value = imageStore.imagesMap[atlas_id]
  data.value = images.value.map((image) => ({
    sn: image.sn,
    title: image.title,
    size: '0kB',
  }))

  if (images.value.length > 0) {
    imageUrl.value = images.value[0].url
  }

  isImagesLoaded.value = true
  console.log('[BaKaBooru] 图片加载成功', images)
}

onMounted(() => {
  loadImages()
})
</script>

<template>
  <n-layout has-sider sider-placement="right" v-if="isImagesLoaded">
    <n-layout-content
      :native-scrollbar="false"
      content-style="
          height:100%;
          display: flex;
          justify-content: center;
          align-items: center;
        "
    >
      <n-image :src="imageUrl" width="100%" object-fit="contain" />
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
          <ImageTable
            :columns="columns"
            :data="data"
            :row-props="
              (row: ImageRow) => {
                return {
                  style: 'cursor: pointer;',
                  onClick: () => {
                    imageUrl = images[row.sn].url
                  },
                }
              }
            "
          />
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
