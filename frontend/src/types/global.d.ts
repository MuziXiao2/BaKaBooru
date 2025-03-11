export interface Message {
  visible: boolean
  content: string
  type: 'info' | 'success' | 'error' | 'warning'
  duration: number
}

export interface GlobalUiState {
  message: Message
  isDarkMode: boolean
}
