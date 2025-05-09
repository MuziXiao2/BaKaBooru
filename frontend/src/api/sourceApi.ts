import type { SourceReferenceDTO } from '@/types'
import api from '@/api/index.ts'

const baseUrl = 'http://localhost:8080/source'

export async function getAllSources(): Promise<Source[]> {
  const response = await api.get(baseUrl)
  return response.data.data
}

export async function getSource(sourceId: number) {
  const response = await api.get(baseUrl + `/${sourceId}`)
  return response.data.data
}

export async function addSource(sourceReferenceDTO: SourceReferenceDTO) {
  const response = await api.post(baseUrl, sourceReferenceDTO)
  return response.data.data
}
