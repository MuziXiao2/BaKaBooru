import api from '@/api/index.ts'
import { baseUrl } from '@/api/index.ts'
import type { ImageDetail, ImageQuery, ImageQueryParams, ImageRequest, Page } from '@/types'
import qs from 'qs'

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

//更新图片详情
export async function updateImageDescription(
  uuid: string,
  description: string,
): Promise<ImageDetail> {
  const response = await api.put(baseUrl + `/image/${uuid}/description`, description, {
    headers: { 'Content-Type': 'text/plain' },
  })
  return response.data.data
}

//查询图片
export async function queryImages(params: ImageQueryParams): Promise<Page<ImageQuery>> {
  const response = await api.get(baseUrl + '/image', {
    params,
    paramsSerializer: (params) =>
      qs.stringify(params, {
        indices: false,
        allowDots: true,
      }),
  })
  return response.data.data
}
