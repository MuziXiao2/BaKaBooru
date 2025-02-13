import api from './index';

/**
 * 给图集添加图片
 * @param {number} atlasId 图集 ID
 * @param {Object} data 图片请求数据
 * @returns {Promise} 返回添加图片的请求结果
 */
export function addImage( data) {
  return api.post(`/sources`, data);
}

/**
 * 获取图集下所有图片
 * @param {number} atlasId 图集 ID
 * @returns {Promise} 返回图集下的图片列表
 */
export function getAllImages(atlasId) {
  return api.get(`/images/{atlas_id}`);
}
