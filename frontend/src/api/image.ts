import api from './index'
import type { AxiosResponse } from 'axios'
import type Image from '@/types/Image'

/**
 * 给图集添加图片
 * @param {number} atlasId 图集 ID
 * @param {Object} data 图片请求数据
 * @returns {Promise} 返回添加图片的请求结果
 */
export function addImage() {}

/**
 * 获取图集下所有图片
 * @returns {Promise} 返回图集下的图片列表
 * @param atlas_id
 */
export function getAllImage(atlas_id: number):Promise<AxiosResponse<Image[]>> {
  return api.get(`/images/${atlas_id}`)
}
