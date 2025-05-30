import type { ImageTagQueryRequest } from '@/types/tag.ts'

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
  tags: ImageTagQueryRequest
  sortBy: string
  sortDirection: string
}

export * from './image'
export * from './file'
export * from './tag'
