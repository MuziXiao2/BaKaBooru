export interface MenuOption {
  key: string;
  icon?: any;
  label: string;
  show?: boolean;
  disabled?: boolean;
}


export interface MenuGroupOption {
  type: string;
  label: string;
  key: string;
  show?: boolean;
  children: MenuOption[]
}

export interface MenuGroupOptionMap {
  [key: string]: SourceOption;
}
