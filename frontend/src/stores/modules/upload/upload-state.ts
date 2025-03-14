import { defineStore } from 'pinia'
import { addAtlas, addImage } from '@/api'
import type { ImageRequestDTO, AtlasRequestDTO, CustomTreeOption } from '@/types'

export const useUploadStateStore = defineStore('upload-state', {
  state: () => ({
    atlasesInfo: {} as { [key: string]: AtlasRequestDTO },
    imagesInfo: {} as { [key: string]: ImageRequestDTO },

    data: [] as Array<CustomTreeOption>,

    expandedKeys: [] as Array<string>,
    checkedKeys: [] as Array<string>,
  }),
  getters: {},
  actions: {
    addData(title: string, uuid: string, extension: string, size: number) {
      const atlasKey = `${this.data.length}`
      const imageKey = uuid

      this.atlasesInfo[atlasKey] = { title: title, creator: 'xiao2', sourceId: 1 }
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
        const response = await addAtlas(atlas)
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
