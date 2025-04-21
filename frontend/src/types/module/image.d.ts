export interface Image {
  id: number
  atlasId: number
  uuid: string

  title: string
  extension: string
  size: number
}

export interface ImageRequestDTO {
  uuid: string
  title: string
  extension: string
  size: number
  atlasId: number
}
export interface ImageResponseDTO {
  uuid: string
  title: string
  extension: string
  size: number
}
