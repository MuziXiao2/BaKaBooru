
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

export interface SearchForm {
  keyword: string
  tags: string[]
  sortBy: string
  sortDirection: string
}

export * from './image'
export * from './file'
export * from './tag'
