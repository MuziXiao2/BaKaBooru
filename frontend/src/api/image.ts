import api from './index'
import type { AxiosResponse } from 'axios'
import type Image from '@/types/image'

/**
 * 获取图集下所有图片
 * @returns {Promise} 返回图集下的图片列表
 * @param atlas_id
 */
export function getImages(atlasId: number): Promise<AxiosResponse<Image[]>> {
  return api.get(`/images/${atlasId}`)
}
