export interface Group {
  id: number
  name: string
}

export interface Source {
  id: number
  name: string
  url: string
  groupId: number
  addedAt: never

  defaultName: string
  ossUrl: string
  creator: string
  createdAt: never
  updatedAt: never
}

export interface SourceReferenceDTO {
  name: string | null
  url: string | null
  groupId: number | null
}
