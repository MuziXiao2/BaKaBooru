import api from './index'
import type { AxiosResponse } from 'axios'
import type Source from '@/stores/types/source'

/**
 * 添加图源
 * @param {Object} data 图源请求数据
 * @returns {Promise} 返回添加图源的请求结果
 */
export function addSource() {

}

/**
 * 获取所有图源
 * @returns {Promise} 返回所有图源的列表
 */
export function getSources(): Promise<AxiosResponse<Source[]>> {
  return api.get('/sources')
}
