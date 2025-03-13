export interface Image {
  title: string
  uuid: string
  url: string
  sn: number
  size: number
  atlasId: number
}

export interface ImageRequestDTO {
  title: string
  uuid: string
  extension: string
  url: string
  size: number
}
