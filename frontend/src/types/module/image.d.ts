export interface Image {
  id: number
  sn: number
  atlasId: number

  uuid: string
  title: string
  extension: string
  size: number
}

export interface ImageRequestDTO {
  uuid: string
  atlasId: number

  title: string
  extension: string
  size: number
}

export interface ImageResponseDTO {
  id: number
  uuid: string
  sn: number
  atlasId: number

  title: string
  extension: string
  size: number
}
