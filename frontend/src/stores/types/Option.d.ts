export interface MenuOption {
  key: string;
  label: string | function;
  icon?: any;
  show?: boolean;
  disabled?: boolean;
}


export interface MenuGroupOption {
  type: string;
  key: string;
  label: string;
  show?: boolean;
  children: MenuOption[]
}

export interface MenuGroupOptionMap {
  [key: string]: MenuGroupOption;
}
