import type { CSSProperties } from 'vue'

export interface IVirtualWaterFallProps {
  column?: number
  columnItemCount?: number
  requestSize?: number
  gap?: number
  request?: FsVirtualWaterfallReuqest
}

export interface IDataItem {
  id: string | number
  width: number
  height: number
  [key: string]: any
}

export interface IRenderItem {
  item: IDataItem
  y: number
  h: number
  style: CSSProperties
}

export type FsVirtualWaterfallReuqest = (
  page: number,
  pageSize: number
) => Promise<{ total: number; list: IDataItem[] }>
