export interface Source {
  id: string
  name: string
  url: string
  creator: string
  createdAt: string
  updatedAt: string
  addedAt: string
}

export interface SourceReferenceDTO {
  name: string | null
  url: string
}
