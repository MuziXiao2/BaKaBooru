import { defineStore } from 'pinia'
import { getAllImage } from '@/api'
import type Image from '@/types/Image'

export const useImageStore = defineStore('image', {
  state: () => ({
    // 存储已加载的图片数据
    imagesMap: {} as { [key: string]: Image[] },
  }),
  getters: {},
  actions: {
    async fetchImages(atlas_id: number) {
      try {
        const response = await getAllImage(atlas_id)
        this.imagesMap[atlas_id] = response.data
      } catch (error) {
        console.error('获取图片失败', error)
      }
    },
  },
})
