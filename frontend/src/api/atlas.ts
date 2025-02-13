import api from './index';
import type { Atlas } from '@/types/atlas'

/**
 * 给图源添加图集
 * @param {number} sourceId 图源 ID
 * @param {Object} data 图集请求数据
 * @returns {Promise} 返回添加图集的请求结果
 */
export function addAtlas(sourceId, data) {
  return api.post(`/sources/${sourceId}`, data);
}

/**
 * 获取图源下所有图集
 * @param {number} sourceId 图源 ID
 * @returns {Promise} 返回图源下的图集列表
 */
export function getAllAtlases(sourceId):AxiosResponse<Atlas[]> {
  return api.get(`/sources/${sourceId}`);
}
