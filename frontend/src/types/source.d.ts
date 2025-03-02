export default interface SourceGroup {
  id: number
  sn: number
  name: string
}

export default interface Source {
  id: number
  name: string
  type: string
  url: string
  groupId: number
  createAt: Date
  updatedAt: Date
}
