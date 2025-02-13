import { defineStore } from 'pinia'
import { useSiderStore } from '@/stores/siderStore.ts'
import { getAllAtlases } from '@/api'

export const useAtlasStore = defineStore('atlas', {
  state: () => ({
    loadedAtlases: [],  // 存储图集数据
    loadedSelectedSourceKey: null  // 存储已加载图源的 key
  }),
  getters: {
    async atlases(state) {
      const siderStore = useSiderStore()
      const selectedSourceKey = siderStore.selectedSourceKey

      // 如果 selectedSourceKey 没有变化，直接返回现有数据
      if (selectedSourceKey === state.loadedSelectedSourceKey) {
        return state.loadedAtlases
      }

      // 如果 selectedSourceKey 变化，重新加载数据
      try {
        const response = await getAllAtlases(selectedSourceKey)
        state.loadedAtlases = response.data  // 更新图集数据
        state.loadedSelectedSourceKey = selectedSourceKey  // 更新已加载的图源 key
        return response.data
      } catch (error) {
        console.error('获取图集失败', error)
        return []
      }
    }
  }
})
