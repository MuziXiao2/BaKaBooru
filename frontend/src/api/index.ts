import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 10000
})

// 设置请求拦截器（例如：添加 Token 到请求头）
api.interceptors.request.use(config => {
  // 假设从 localStorage 获取用户 Token
  const token = localStorage.getItem('token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
}, error => {
  return Promise.reject(error)
})

// 设置响应拦截器（处理错误等）
api.interceptors.response.use(response => {
  return response
}, error => {
  console.error('API 请求错误:', error)
  return Promise.reject(error)
})

export default api
export * from './source';
export * from './atlas';
export * from './image';
