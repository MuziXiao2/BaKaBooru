import { defineStore } from 'pinia'
import { createAtlas, addImage } from '@/api'
import type { ImageRequestDTO, AtlasRequestDTO, CustomTreeOption, Source } from '@/types'
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
    addData(title: string, uuid: string, extension: string, size: number) {
      const atlasKey = `${this.data.length}`
      const imageKey = uuid

      this.atlasesInfo[atlasKey] = { title: title, creator: 'xiao2' }
      this.imagesInfo[imageKey] = {
        atlasId: undefined,
        uuid: uuid,
        title: title,
        extension: extension,
        size: size,
      }

      this.data.push({
        title: title,
        key: atlasKey,
        type: 'Atlas',
        children: [{ title: title, key: imageKey, type: 'Image' }],
      } as CustomTreeOption)
    },

    async saveData() {
      for (const d of this.data) {
        const atlas = this.atlasesInfo[d.key]
        const response = await createAtlas(atlas)
        const atlasId = response.data.data.id
        for (const i of d.children) {
          const image = this.imagesInfo[i.key]
          image.atlasId = atlasId
          await addImage(image)
        }
      }
    },
  },
})
