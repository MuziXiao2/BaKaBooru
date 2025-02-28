import api from './index'
import type Atlas from '@/stores/types/atlas'
import type { AxiosResponse } from 'axios'

/**
 * 获取图源下所有图集
 * @param {number} source_id 图源 ID
 * @returns {Promise} 返回图源下的图集列表
 */
export function getAtlases(source_id: number): Promise<AxiosResponse<Atlas[]>> {
  return api.get(`/atlases/${source_id}`)
}
