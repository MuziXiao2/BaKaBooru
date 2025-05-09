import api from '@/api/index.ts'
import type { ImageReferenceDTO } from '@/types'

const baseUrl = 'http://localhost:8080/image'

export async function getAllImages(sourceId: number, atlasUuid: string) {
  const response = await api.get(baseUrl + `/${sourceId}/${atlasUuid}`)
  return response.data.data
}

export async function addImage(
  sourceId: number,
  atlasUuid: string,
  imageReferenceDTO: ImageReferenceDTO,
) {
  const response = await api.post(baseUrl + `/${sourceId}/${atlasUuid}`, imageReferenceDTO)
  return response.data.data
}

export async function uploadImage(sourceId: number, imageReferenceDTO: ImageReferenceDTO) {
  const response = await api.post(baseUrl + `/${sourceId}/upload`, imageReferenceDTO)
  return response.data.data
}
