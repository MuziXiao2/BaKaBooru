import type { HTMLAttributes } from 'vue'

export interface MenuOption {
  key: string
  label: string | function
  icon?: never
  show?: boolean
  disabled?: boolean
}

export interface MenuGroupOption {
  type: string
  key: string
  label: string
  show?: boolean
  children: MenuOption[]
}

export interface MenuDividerOption {
  key: string
  props: HTMLAttributes
  show?: boolean
  type: string
}
