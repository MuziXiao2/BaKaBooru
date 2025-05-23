import api from '@/api/index.ts'
import type {
  ImageDetail,
  FileDetail,
  ImageQuery,
  ImageQueryParams,
  ImageRequest,
  Page,
} from '@/types'

const baseUrl = 'http://localhost:8080'

//获取图片文件临时URL
export async function getImageFileUrl(hash: string): Promise<string> {
  const response = await api.get(baseUrl + `/file/${hash}`)
  return response.data.data
}

//添加图片
export async function addImage(imageRequestDTO: ImageRequest): Promise<ImageDetail> {
  const response = await api.post(baseUrl + '/image', imageRequestDTO)
  return response.data.data
}

//为图片添加图片文件
export async function addFile(uuid: string, file: File): Promise<FileDetail> {
  const response = await api.post(baseUrl + `/image/${uuid}/file`, file)
  return response.data.data
}

//获取文件详细信息
export async function getFileDetails(uuid: string): Promise<FileDetail[]> {
  const response = await api.get(baseUrl + `/image/${uuid}/file`)
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
