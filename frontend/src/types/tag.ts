export interface ImageTagDetail {
  artist: string[]
  character: string[]
  copyright: string[]
  general: string[]
  meta: string[]
}

export interface ImageTagQueryRequest {
  artist: string[]
  character: string[]
  copyright: string[]
  general: string[]
  meta: string[]
}

export type TagCategory = 'artist' | 'character' | 'copyright' | 'meta' | 'general'
