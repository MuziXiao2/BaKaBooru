import { createRouter, createWebHistory } from 'vue-router'
import View from '@/views/View.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      // 重定向到 /view
      path: '/',
      redirect: '/view',
    },
    {
      // 浏览页
      path: '/view',
      name: 'View',
      component: View,
      meta: { navItem: 'view', title: 'View Atlases' },
    },
  ],
})

export default router
