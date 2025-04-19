export interface Source {
  id: number
  sn: number
  groupId: number

  name: string
  url: string
  ossUrl: string
  createAt: Date
  updatedAt: Date
}

export interface SourceRequestDTO {
  name: string
  port: number
}

export interface SourceReferenceDTO {
  groupId: number | null
  url: string | null
}

export interface Group {
  id: number
  sn: number
  name: string
}
