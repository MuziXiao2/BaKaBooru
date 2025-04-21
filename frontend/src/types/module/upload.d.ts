import type { TreeOption } from 'naive-ui'
import type { ImageResponseDTO } from '@/types'

export interface CustomTreeOption extends TreeOption {
  type: 'Image' | 'Atlas'
  children: Array<ImageResponseDTO & TreeOption>
}
