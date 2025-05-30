import api from '@/api/index.ts'
import { baseUrl } from '@/api/index.ts'
import type { ImageTagDetail } from '@/types'

//添加标签
export async function addTag(uuid: string, name: string, type: string): Promise<ImageTagDetail> {
  const response = await api.post(baseUrl + `/image/${uuid}/tag`, {
    params: {
      name: name,
      type: type,
    },
  })
  return response.data.data
}

//获取标签
export async function getTags(uuid: string): Promise<ImageTagDetail> {
  const response = await api.get(baseUrl + `/image/${uuid}/tag`)
  return response.data.data
}
