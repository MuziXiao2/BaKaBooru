export interface Image {
  uuid:string
  title:string


}


export interface ImageFile {
  hash: string
  title: string
  url: string
  size: number
  width: number
  height: number
  originalFileName: string
  extension: string
}


export interface ImageFileRequestDTO {
  title: string
  hash: string
}


