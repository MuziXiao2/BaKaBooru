export interface Atlas {
  uuid: string
  sourceUuid: string
  title: string
  coverHash: string
  creator: string
  updatedAt: string
  createdAt: string
}

export interface AtlasRequestDTO {
  title: string
  creator: string
  description: string
}
