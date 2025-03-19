import type { Source, AtlasRequestDTO, ImageRequestDTO, Atlas } from '@/types'
import api from '@/api/index.ts'

export async function getAtlases(source: Source) {
  const response = await api.get(source.sourceUrl, {
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

export async function getImages(source: Source, atlas: Atlas) {
  const response = await api.get(source.sourceUrl, {
    params: {
      type: 'image',
      atlas_id: atlas.id,
    },
  })
  return response.data
}

export function addImage(imageRequestDTO: ImageRequestDTO) {
  return api.post('', imageRequestDTO, {
    params: {
      type: 'image',
    },
  })
}
