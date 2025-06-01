import api from '@/api/index.ts'
import { baseUrl } from '@/api/index.ts'

//添加标签
export async function addTags(uuid: string, tag: string): Promise<string[]> {
  const response = await api.post(baseUrl + `/image/${uuid}/tag`, tag, {
    headers: { 'Content-Type': 'text/plain' },
  })
  return response.data.data
}

//更新标签
export async function updateTags(uuid: string, tags: string[]): Promise<string[]> {
  const response = await api.put(baseUrl + `/image/${uuid}/tag`, tags)
  return response.data.data
}

//获取标签
export async function getTags(uuid: string): Promise<string[]> {
  const response = await api.get(baseUrl + `/image/${uuid}/tag`)
  return response.data.data
}
