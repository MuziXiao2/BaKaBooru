import { defineStore } from 'pinia'
import { createAtlas, addImage } from '@/api'
import type { ImageRequestDTO, AtlasRequestDTO, CustomTreeOption } from '@/types'
import type { SelectOption } from 'naive-ui'
import { useSoucreStore } from '@/stores'

export const useUploadStateStore = defineStore('upload-state', {
  state: () => ({
    currentGroupId: null as number | null,
    currentSourceId: null as number | null,

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
    updateGroupSelectOptions() {
      const sourceStore = useSoucreStore()
      this.groupSelectOptions = sourceStore.groups.map((group) => ({
        label: group.name,
        value: group.id,
      }))
    },
    updateSourceSelectOptions() {
      const sourceStore = useSoucreStore()
      if (this.currentGroupId === null) {
        this.sourceSelectOptions = []

      }
      this.sourceSelectOptions = sourceStore.sources[this.currentGroupId].map((source) => ({
        label: source.name,
        value: source.id,
      }))
    },
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
      console.log(this.data)
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
