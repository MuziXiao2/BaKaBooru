import api from '@/api/index.ts'
import type { AtlasRequestDTO } from '@/types'

const baseUrl = 'http://localhost:8080/atlas'

export async function getAllAtlases(sourceId: string) {
  const response = await api.get(baseUrl + `/${sourceId}`)
  return response.data.data
}

export async function getAtlas(sourceId: number, atlasUuid: string) {
  const response = await api.get(baseUrl + `/${sourceId}/${atlasUuid}`)
  return response.data.data
}

export async function addAtlas(sourceId: number, atlasRequestDTO: AtlasRequestDTO) {
  const response = await api.post(baseUrl + `/${sourceId}`, atlasRequestDTO)
  return response.data.data
}
