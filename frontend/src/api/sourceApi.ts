import type { AxiosResponse } from 'axios'
import type { Atlas, AtlasRequestDTO, ImageRequestDTO, ImageResponseDTO } from '@/types'
import api from '@/api/index.ts'

export function getAtlases(sourceId: number): Promise<AxiosResponse<Atlas[]>> {
  return api.get('', {
    params: {
      type: 'atlas',
      source_id: sourceId,
    },
  })
}

export function addAtlas(atlas: AtlasRequestDTO) {
  return api.post('', atlas, {
    params: {
      type: 'atlas',
    },
  })
}

export function getImages(atlasId: number): Promise<AxiosResponse<ImageResponseDTO[]>> {
  return api.get('', {
    params: {
      type: 'image',
      atlas_id: atlasId,
    },
  })
}

export function addImage(imageRequestDTO: ImageRequestDTO) {
  return api.post('', imageRequestDTO, {
    params: {
      type: 'image',
    },
  })
}
