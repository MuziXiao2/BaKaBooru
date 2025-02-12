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
  key: string,
  name: string,
  type: string,
  count: number,
  atlases: Atlas[],
}
