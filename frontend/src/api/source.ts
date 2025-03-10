import api from './index'
import type { AxiosResponse } from 'axios'
import type { Source, SourceGroup } from '@/types/source'

/**
 * 创建图源组
 *
 */
export function createSourceGroups(name: string) {
  return api.post('/groups', { name })
}


/**
 * 添加图源
 *
 */
export function addSource(source) {
  return api.post('/sources', source)
}


/**
 * 获取所有图源组
 * @returns {Promise} 返回所有图源组的列表
 */
export function getSourceGroups(): Promise<AxiosResponse<SourceGroup[]>> {
  return api.get(`/groups`)
}

/**
 * 获取所有图源
 * @returns {Promise} 返回所有图源的列表
 */
export function getSources(sourceGroup: SourceGroup): Promise<AxiosResponse<Source[]>> {
  return api.get(`/sources?groupId=${sourceGroup.id}`)
}
