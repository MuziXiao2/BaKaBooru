import type { ImageTagQueryRequest } from '@/types/tag.ts'

export interface ImageDetail {
  uuid: string
  title: string
  creator: string
  description: string
  createdAt: string
  updatedAt: string
  files: string[]
  tags: string[]
}

export interface ImageQuery {
  uuid: string
  title: string
  coverHash: string
}

export interface ImageRequest {
  title: string
  creator: string
  description: string
}

export interface ImageQueryParams {
  keyword?: string
  tags?: ImageTagQueryRequest
  sortBy?: string
  sortDirection?: string
  page?: number
  size?: number
}
