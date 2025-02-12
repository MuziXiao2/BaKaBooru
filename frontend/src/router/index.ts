import { createRouter, createWebHistory } from 'vue-router'
import AtlasesViews from '@/views/AtlasesViews.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/atlases'  // 重定向到 /home
    },
    {
      path: '/atlases',
      name: 'atlases',
      component: AtlasesViews
    }
  ]
})

export default router
