import { defineStore } from 'pinia'
import { getAllAtlas} from '@/api'
import type Atlas from '@/types/Atlas'

export const useAtlasStore = defineStore('atlas', {
  state: () => ({
    // 存储已加载的图集数据
    atlasesMap: {} as { [key: string]: Atlas[] }
  }),
  getters: {


  },
  actions: {
    async fetchAtlases(id: string) {
      try {
        const response = await getAllAtlas(+id)
        this.atlasesMap[id] = response.data
      } catch (error) {
        console.error('获取图集失败', error)
      }
    }
  }
})
