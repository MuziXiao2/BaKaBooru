import { type Component, h } from 'vue'
import { type MenuDividerOption, type MenuOption, NIcon } from 'naive-ui'

function renderIcon(icon: Component) {
  return () => h(NIcon, null, { default: () => h(icon) })
}

export function createMenuOption(key: string, label: string, icon: Component): MenuOption {
  return {
    key: key,
    label: label,
    icon: renderIcon(icon),
  }
}

export function createMenuDividerOption(key: string): MenuDividerOption {
  return {
    key: key,
    type: 'divider',
  }
}
