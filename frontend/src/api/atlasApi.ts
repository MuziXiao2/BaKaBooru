import api from '@/api/index.ts'
import type { Atlas, AtlasRequestDTO } from '@/types'

const baseUrl = 'http://localhost:8080'

export async function getAtlas(sourceUuid: string, atlasUuid: string) {
  const response = await api.get(baseUrl + `/source/${sourceUuid}/atlas/${atlasUuid}`)
  return response.data.data
}

export async function getAllAtlases(sourceUuid: string): Promise<Atlas[]> {
  const response = await api.get(baseUrl + `/source/${sourceUuid}/atlas`)
  return response.data.data
}

export async function addAtlas(sourceUuid: string, atlasRequestDTO: AtlasRequestDTO) {
  const response = await api.post(baseUrl + `/source/${sourceUuid}`, atlasRequestDTO)
  return response.data.data
}
