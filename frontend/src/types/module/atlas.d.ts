export interface Atlas {
  id: number
  title: string
  coverUrl: string
  creator: string
  updatedAt: Date
  createAt: Date
  sourceId: number
}

export interface AtlasRequestDTO {
  title: string
  creator: string
  sourceId: number
}

export interface AtlasResponseDTO {
  id: number
  title: string
  coverUrl: string
  creator: string
  updatedAt: Date
  createAt: Date
  sourceId: number
}
