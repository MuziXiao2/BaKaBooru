export interface Message {
  visible: boolean
  content: string
  type: 'info' | 'success' | 'error' | 'warning'
  duration: number
}

export interface ContextMenu {
  visible: boolean
  x: number
  y: number

  [key: string]: never
}

export interface GlobalUiState {
  message: Message
  isDarkMode: boolean
}
