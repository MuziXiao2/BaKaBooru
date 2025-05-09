import { defineStore } from 'pinia'
import type {
  ImageRequestDTO,
  AtlasRequestDTO,
  CustomTreeOption,
  Source,
  ImageResponseDTO,
} from '@/types'
import type { SelectOption } from 'naive-ui'

export const useUploadStateStore = defineStore('upload-state', {
  state: () => ({
    currentSource: null as Source | null,

    groupSelectOptions: [] as SelectOption[],
    sourceSelectOptions: [] as SelectOption[],

    data: [] as Array<CustomTreeOption>,

    expandedKeys: [] as Array<string>,
    checkedKeys: [] as Array<string>,

    atlasesInfo: {} as { [key: string]: AtlasRequestDTO },
    imagesInfo: {} as { [key: string]: ImageRequestDTO },
  }),
  getters: {},
  actions: {
    addData(image: ImageResponseDTO) {
      this.data.push({
        title: image.title,
        creator: 'xiao2',
        key: this.data.length,
        type: 'Atlas',
        children: [
          {
            uuid: image.uuid,
            title: image.title,
            extension: image.extension,
            size: image.size,
            key: image.uuid,
            type: 'Image',
          },
        ],
      })
    },

    async saveData() {},
  },
})
