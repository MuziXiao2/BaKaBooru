import api from '@/api/index.ts'
import type { ImageReferenceDTO } from '@/types'

const baseUrl = 'http://localhost:8080/image'

export async function getImage(sourceUuid: string, imageHash: string) {
  const response = await api.get(baseUrl + `/source/${sourceUuid}/image/${imageHash}`)
  return response.data.data
}

export async function getAllImages(sourceUuid: string, atlasUuid: string) {
  const response = await api.get(baseUrl + `/source/${sourceUuid}/atlas/${atlasUuid}/image`)
  return response.data.data
}

export async function addImage(
  sourceUuid: string,
  atlasUuid: string,
  imageReferenceDTO: ImageReferenceDTO,
) {
  const response = await api.post(
    baseUrl + `/source/${sourceUuid}/atlas/${atlasUuid}/image`,
    imageReferenceDTO,
  )
  return response.data.data
}

export async function uploadImage(sourceUuid: string, imageReferenceDTO: ImageReferenceDTO) {
  const response = await api.post(baseUrl + `/source/${sourceUuid}/upload`, imageReferenceDTO)
  return response.data.data
}
