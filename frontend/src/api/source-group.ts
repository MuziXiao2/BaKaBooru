import type { AxiosResponse } from 'axios'
import api from '@/api/index.ts'
import type SourceGroup from '@/types/source-group'

/**
 * 获取所有图源组
 * @returns {Promise} 返回所有图源组的列表
 */
export function getSourceGroups(): Promise<AxiosResponse<SourceGroup[]>> {
  return api.get(`/source-groups`)
}
