export interface ImageDetail {
  uuid: string
  title: string
  creator: string
  description: string
  createdAt: string
  updatedAt: string
  files: ImageFile[]
  tag: Tag[]
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
  tags?: number[]
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

export interface Tag {
  id: number
  name: string
  type: string
  count:number
}

export interface Page<T> {
  content: T[]
  totalElements: number
  totalPages: number
  pageNumber: number
  pageSize: number
}
