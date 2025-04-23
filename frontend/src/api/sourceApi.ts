import type { Source, Atlas } from '@/types'
import api from '@/api/index.ts'

export async function getSourceInfo(source: Source) {
  const response = await api.get(source.url, {
    params: {
      type: 'source',
    },
  })
  return response.data.data
}

export async function getAtlases(source: Source) {
  const response = await api.get(source.url, {
    params: {
      type: 'atlas',
    },
  })
  return response.data.data
}

export async function getImages(source: Source, atlas: Atlas) {
  const response = await api.get(source.url, {
    params: {
      type: 'image',
      atlas_id: atlas.id,
    },
  })
  return response.data.data
}

export async function createAtlas(source, atlas) {
  const response = await api.post(source.url, atlas, {
    params: {
      type: 'atlas',
    },
  })
  return response.data.data
}

export async function addImage(source, image) {
  const response = await api.post(source.url, image, {
    params: {
      type: 'image',
    },
  })
  return response.data.data
}
