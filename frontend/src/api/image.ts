import api from '@/api/index.ts'

const baseUrl = 'http://localhost:8080/image'

//获取图片文件
export async function getImageFile(hash: string) {
  const response = await api.get(baseUrl + `/file/${hash}`)
  return response.data.data
}

//添加图片
export async function addImage(imageRequestDTO: ImageRequestDTO) {
  const response = await api.post(baseUrl, imageRequestDTO)
  return response.data.data
}

//获取图片
export async function getImage(uuid: string) {
  const response = await api.get(baseUrl + `/${uuid}`)
  return response.data.data
}

//获取所有图片
export async function getAllImages() {
  const response = await api.get(baseUrl)
  return response.data.data
}

//为图片添加图片文件
export async function addImageFile(uuid: string, imageFileRequestDTO: ImageFileRequestDTO) {
  const response = await api.post(baseUrl + `/${uuid}`, imageFileRequestDTO)
  return response.data.data
}

//获取所有图片文件
export async function getAllImageFiles(uuid: string) {
  const response = await api.get(baseUrl + `/${uuid}/file`)
  return response.data.data
}
