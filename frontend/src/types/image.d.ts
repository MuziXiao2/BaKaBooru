export interface Image {
  id: number
  uuid: string
  size: number
  title: string
  extension: string
  atlasId: number
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
