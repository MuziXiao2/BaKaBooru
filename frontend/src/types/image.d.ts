export interface Image {
  uuid: string
  title: string
  creator: string
  description: string
  createdAt: string
  updatedAt: string
  files: ImageFile[]
}

export interface ImageRequestDTO {
  title: string
  creator: string
  description: string
}

export interface ImageFile {
  hash: string
  type: string
  fileName: string
  width: number
  height: number
  size: number
}

export interface ImageQueryParams {
  title?: string
  tags?: string
  page?: number
  size?: number
}

export interface ImageQuery {
  uuid: string
  title: string
  coverHash: string
}
