export interface Image {
  id: number,
  url: string
}

export interface Atlas {
  id: number,
  name: string,
  images: Image[]
}

export interface Source {
  name: string,
  type: string,
  atlases: Atlas[],
}
