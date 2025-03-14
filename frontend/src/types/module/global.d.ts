export interface Message {
  visible: boolean
  content: string
  type: 'info' | 'success' | 'error' | 'warning'
  duration: number
}
