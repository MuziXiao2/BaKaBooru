export interface Source {
  sn: number
  groupId: number

  name: string
  type: string
  sourceUrl: string
  ossUrl: string
  createAt: Date
  updatedAt: Date
}

export interface SourceRequestDTO {
  name: string
  port: number
}

export interface SourceReferenceDTO {
  groupId: number

  type: string
  sourceUrl: string
}

export interface SourceResponseDTO {
  sn: number
  groupId: number

  name: string
  type: string
  sourceUrl: string
  ossUrl: string
  createAt: Date
  updatedAt: Date
}

export interface Group {
  id: number
  sn: number
  name: string
}
