import { defineStore } from 'pinia'
import type Source from '@/stores/types/source'
import type Image from '@/stores/types/image'
import type Atlas from '@/stores/types/atlas'
import { getAtlases, getImages, getSources } from '@/api'
import { NButton } from 'naive-ui'
import type { MenuOption, MenuGroupOption, MenuDividerOption } from '@/stores/types/Option'
import { h } from 'vue'
import { renderIcon } from '@/utils/icon.ts'
import {
  HomeOutline as HomeIcon,
  InformationCircleOutline as TestIcon,
  StarOutline as StarIcon,
} from '@vicons/ionicons5'

export const useViewUiStore = defineStore('view', {
  state: () => ({
    isLoading: false,

    isSiderCollapsed: false,
    showAtlasInfo: false,

    isSourcesLoaded: false,
    sources: [] as Source[],
    currentSource: null as Source | null,

    isAtlasesLoaded: false,
    atlases: [] as Atlas[],
    currentAtlas: null as Atlas | null,

    isImagesLoaded: false,
    images: [] as Image[],
    currentImage: null as Image | null,

    contextMenu: {
      visible: false,
      x: 0,
      y: 0,
      atlas: null,
    },
  }),
  getters: {
    // 菜单选项
    options(state): Array<MenuOption | MenuGroupOption> {
      // 默认选项
      const defaultOption: MenuOption = {
        key: 'default',
        label: () =>
          h(
            NButton,
            {
              text: true,
            },
            { default: () => '主页' },
          ),
        icon: renderIcon(HomeIcon),
        show: true,
      }

      // 图源选项
      const sourceOption: { [key: string]: MenuGroupOption } = {
        local: {
          type: 'group',
          label: '本地图源',
          key: 'local',
          show: true,
          children: [],
        },
        network: {
          type: 'group',
          label: '网络图源',
          key: 'network',
          show: true,
          children: [],
        },
      }

      // 将图源添加到对应type下的children
      state.sources.forEach((source) => {
        sourceOption[source.type].children.push({
          key: `${source.id}`,
          label: () =>
            h(
              NButton,
              {
                text: true,
              },
              { default: () => source.name },
            ),
          icon: renderIcon(TestIcon),
          show: true,
          disabled: false,
        })
      })

      // 其他选项
      const otherOption: Array<MenuDividerOption> = [
        {
          key: 'divider',
          type: 'divider',
          props: {
            style: {
              marginLeft: '32px',
            },
          },
        },
        {
          label: '收藏',
          key: 'favorite',
          icon: renderIcon(StarIcon),
        },
      ]

      return [defaultOption, sourceOption.local, sourceOption.network, ...otherOption]
    },
  },
  actions: {
    startLoading() {
      this.isLoading = true
    },
    stopLoading() {
      this.isLoading = false
    },
    async fetchSources() {
      const response = await getSources()
      this.sources = response.data
      this.isSourcesLoaded = true
    },
    async fetchAtlases() {
      if (!this.currentSource) return
      const response = await getAtlases(this.currentSource.id)
      this.atlases = response.data
      this.isAtlasesLoaded = true
    },
    async fetchImages() {
      if (!this.currentAtlas) return
      const response = await getImages(this.currentAtlas.id)
      this.images = response.data
      this.isImagesLoaded = true
    },
    setCurrentSource(source: Source) {
      this.currentSource = source
    },

    setCurrentAtlas(atlas: Atlas) {
      this.currentAtlas = atlas
    },

    setCurrentImage(image: Image) {
      this.currentImage = image
    },

    openAtlasInfo() {
      this.showAtlasInfo = true
    },

    closeAtlasInfo() {
      this.showAtlasInfo = false
    },

    toggleSider() {
      this.isSiderCollapsed = !this.isSiderCollapsed
    },
  },
})
