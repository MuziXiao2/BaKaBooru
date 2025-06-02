import api from '@/api/index.ts'
import { baseUrl } from '@/api/index.ts'
import type { FileDetail } from '@/types'

//为图片添加图片文件
export async function addFile(uuid: string, file: File): Promise<FileDetail> {
  const formData = new FormData()
  formData.append('file', file)
  const response = await api.post(`${baseUrl}/image/${uuid}/file`, formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  })
  return response.data.data
}

//获取文件详细信息
export async function getFileDetails(uuid: string): Promise<FileDetail[]> {
  const response = await api.get(baseUrl + `/image/${uuid}/file`)
  return response.data.data
}

//获取图片文件临时URL
export async function getImageFileUrl(hash: string): Promise<string> {
  const response = await api.get(baseUrl + `/file/${hash}`)
  return response.data.data
}
