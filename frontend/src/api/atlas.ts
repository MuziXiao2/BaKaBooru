import api from './index'
import type Atlas from '@/types/Atlas'
import type { AxiosResponse } from 'axios'

/**
 * 给图源添加图集
 * @param {Object} data 图集请求数据
 * @returns {Promise} 返回添加图集的请求结果
 */
export function addAtlas() {

}

/**
 * 获取图源下所有图集
 * @param {number} source_id 图源 ID
 * @returns {Promise} 返回图源下的图集列表
 */
export function getAllAtlas(source_id: number): Promise<AxiosResponse<Atlas[]>> {
  return api.get(`/atlases/${source_id}`)
}
