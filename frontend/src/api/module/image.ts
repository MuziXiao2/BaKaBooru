import api from '../index.ts'
import type { AxiosResponse } from 'axios'
import type { ImageResponseDTO, ImageRequestDTO } from '@/types'

export function getImages(atlasId: number): Promise<AxiosResponse<ImageResponseDTO[]>> {
  return api.get(`/images/${atlasId}`)
}

export function addImage(imageRequestDTO: ImageRequestDTO) {
  return api.post('/images', imageRequestDTO)
}
