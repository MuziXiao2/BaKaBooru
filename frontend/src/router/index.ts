import { createRouter, createWebHistory } from 'vue-router'
import View from '@/views/View.vue'
import Upload from '@/views/Upload.vue'
import Tag from '@/views/Tag.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      // 默认
      path: '/',
      redirect: '/view',
    },
    {
      // 浏览页
      path: '/view',
      name: 'View',
      component: View,
      meta: { navItem: 'view', title: 'View' },
    },
    {
      // 上传页
      path: '/upload',
      name: 'Upload',
      component: Upload,
      meta: { navItem: 'upload', title: 'Upload' },
    },
    {
      // 标签页
      path: '/tag',
      name: 'Tag',
      component: Tag,
      meta: { navItem: 'tag', title: 'Tag' },
    },
    {
      // 设置页
      path: '/tag',
      name: 'Tag',
      component: Tag,
      meta: { navItem: 'tag', title: 'Tag' },
    },
  ],
})

export default router
