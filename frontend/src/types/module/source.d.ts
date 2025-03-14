export interface Source {
  id: number
  groupId: number

  name: string
  type: string
  url: string
  createAt: Date
  updatedAt: Date
}

export interface SourceRequestDTO {
  groupId: number

  name: string
  type: string
  url: string
}

export interface SourceResponseDTO {
  id: number
  groupId: number

  name: string
  type: string
  url: string
  createAt: Date
  updatedAt: Date
}

export interface Group {
  id: number
  sn: number
  name: string
}
