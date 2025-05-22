export interface ImageDetail {
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
  keyword?: string
  tags?: string
  sortBy?: 'title' | 'createdAt' | 'updatedAt'
  sortDirection?: 'asc' | 'desc'
  page?: number
  size?: number
}

export interface ImageQuery {
  uuid: string
  title: string
  coverHash: string
}

export interface ImageItem {
  uuid: string
  title: string
  url: string
}

export interface Page<T> {
  content: T[]
  totalElements: number
  totalPages: number
  pageNumber: number
  pageSize: number
}
