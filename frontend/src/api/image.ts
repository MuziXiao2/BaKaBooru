import api from '@/api/index.ts'
import { baseUrl } from '@/api/index.ts'
import type { ImageDetail, ImageQuery, ImageQueryParams, ImageRequest, Page } from '@/types'

//添加图片
export async function addImage(imageRequestDTO: ImageRequest): Promise<ImageDetail> {
  const response = await api.post(baseUrl + '/image', imageRequestDTO)
  return response.data.data
}

//获取图片
export async function getImage(uuid: string): Promise<ImageDetail> {
  const response = await api.get(baseUrl + `/image/${uuid}`)
  return response.data.data
}

//查询图片
export async function queryImages(params: ImageQueryParams): Promise<Page<ImageQuery>> {
  const response = await api.get(baseUrl + '/image', { params })
  return response.data.data
}
