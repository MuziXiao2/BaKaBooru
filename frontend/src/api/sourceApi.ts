import type { Source, AtlasRequestDTO, ImageRequestDTO } from '@/types'
import api from '@/api/index.ts'

export async function getAtlases(source: Source) {
  const response = await api.get(source.url, {
    params: {
      type: 'atlas',
    },
  })
  return response.data
}

export function createAtlas(atlas: AtlasRequestDTO) {
  return api.post('', atlas, {
    params: {
      type: 'atlas',
    },
  })
}

export function getImages(atlasId: number) {
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
