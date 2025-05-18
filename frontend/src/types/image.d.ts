export interface Image {
  uuid: string
  title: string
  creator: string
  description: string
  createdAt: string
  updatedAt: string
}

export interface ImageRequestDTO {
  title: string
  creator: string
  description: string
}

export interface ImageFile {
  hash: string
  title: string
  url: string
  size: number
  width: number
  height: number
  originalFileName: string
  extension: string
}

export interface ImageFileRequestDTO {
  title: string
  hash: string
}
