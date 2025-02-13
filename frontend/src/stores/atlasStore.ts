import { defineStore } from 'pinia'
import { useSiderStore } from '@/stores/siderStore.ts'
import { getAllAtlases } from '@/api'
import type { Atlas } from '@/types/atlas'

export const useAtlasStore = defineStore('atlas', {
  state: () => ({
    loadedAtlases: [],  // 存储图集数据
    loadedSelectedSourceId: null  // 存储已加载图源的 id
  }),
  getters: {
    // 通过 getter 自动获取图集
    atlases(state): Atlas[] {
      const siderStore = useSiderStore()
      const selectedSourceId = siderStore.selectedSourceId

      // 如果 selectedSourceId 没有变化，直接返回现有数据
      if (selectedSourceId === state.loadedSelectedSourceId) {
        return state.loadedAtlases
      }

      // 如果 selectedSourceId 变化，重新加载数据
      getAllAtlases(selectedSourceId).then(response => {
        state.loadedAtlases = response.data  // 更新图集数据
        state.loadedSelectedSourceId = selectedSourceId  // 更新已加载的图源id
        console.log('[BaKaBooru] 获取图集成功',state.loadedAtlases)
      }).catch(error => {
        console.error('获取图集失败', error)
        state.loadedAtlases = []
      })

      return state.loadedAtlases  // 返回当前的图集数据，尚未更新的情况下返回空数据
    }
  }
})
