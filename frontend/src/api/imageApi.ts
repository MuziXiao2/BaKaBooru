import api from '@/api/index.ts'
import type { ImageReferenceDTO } from '@/types'

const baseUrl = 'http://localhost:8080/image'

export async function getImage(sourceId: number, imageHash: string) {
  const response = await api.get(baseUrl + `/source/${sourceId}/image/${imageHash}`)
  return response.data.data
}

export async function getAllImages(sourceId: number, atlasUuid: string) {
  const response = await api.get(baseUrl + `/source/${sourceId}/atlas/${atlasUuid}/image`)
  return response.data.data
}

export async function addImage(
  sourceId: number,
  atlasUuid: string,
  imageReferenceDTO: ImageReferenceDTO,
) {
  const response = await api.post(
    baseUrl + `/source/${sourceId}/atlas/${atlasUuid}/image`,
    imageReferenceDTO,
  )
  return response.data.data
}

export async function uploadImage(sourceId: number, imageReferenceDTO: ImageReferenceDTO) {
  const response = await api.post(baseUrl + `/source/${sourceId}/upload`, imageReferenceDTO)
  return response.data.data
}
