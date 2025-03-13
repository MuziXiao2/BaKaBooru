export interface Atlas {
  id: number
  title: string
  cover_url: string
  creator: string
  updated_at: Date
  create_at: Date
  source_id: number
}

export interface AtlasRequestDTO {
  title: string
  creator: string
  source_id: number
}
