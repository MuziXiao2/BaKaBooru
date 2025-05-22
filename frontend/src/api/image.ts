import api from '@/api/index.ts'
import type { ImageDetail, ImageFile, ImageQuery, ImageQueryParams, ImageRequestDTO, Page } from '@/types'

const baseUrl = 'http://localhost:8080/image'

//获取图片文件临时URL
export async function getImageFileUrl(hash: string): Promise<string> {
  const response = await api.get(baseUrl + `/file/${hash}`)
  return response.data.data
}

//添加图片
export async function addImage(imageRequestDTO: ImageRequestDTO): Promise<ImageDetail> {
  const response = await api.post(baseUrl, imageRequestDTO)
  return response.data.data
}

//为图片添加图片文件
export async function addImageFile(uuid: string, file: File): Promise<ImageFile> {
  const response = await api.post(baseUrl + `/${uuid}`, file)
  return response.data.data
}

//获取图片
export async function getImage(uuid: string): Promise<ImageDetail> {
  const response = await api.get(baseUrl + `/${uuid}`)
  return response.data.data
}

//查询图片
export async function queryImages(params: ImageQueryParams): Promise<Page<ImageQuery>> {
  const response = await api.get(baseUrl, { params })
  return response.data.data
}
