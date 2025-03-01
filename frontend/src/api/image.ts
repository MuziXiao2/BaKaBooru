import api from './index'
import type { AxiosResponse } from 'axios'
import type Image from '@/types/image'


export function getImages(atlasId: number): Promise<AxiosResponse<Image[]>> {
  return api.get(`/images/${atlasId}`)
}

export function addImage(title: string, url: string, atlasId: number) {
  const image = {
    title: title,
    url: url,
    atlas_id: atlasId,
  }
  return api.post('/images', image)
}
