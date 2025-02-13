import api from './index'
import type { Atlas } from '@/types/atlas'

/**
 * 给图源添加图集
 * @param {Object} data 图集请求数据
 * @returns {Promise} 返回添加图集的请求结果
 */
export function addAtlas(data) {
  return api.post(`/atlases`, data)
}

/**
 * 获取图源下所有图集
 * @param {number} source_id 图源 ID
 * @returns {Promise} 返回图源下的图集列表
 */
export function getAllAtlases(source_id): AxiosResponse<Atlas[]> {
  return api.get(`/atlases/${source_id}`)
}
