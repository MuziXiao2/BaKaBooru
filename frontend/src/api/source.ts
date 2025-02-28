import api from './index'
import type { AxiosResponse } from 'axios'
import type Source from '@/types/source'

/**
 * 获取所有图源
 * @returns {Promise} 返回所有图源的列表
 */
export function getSources(): Promise<AxiosResponse<Source[]>> {
  return api.get('/sources')
}
