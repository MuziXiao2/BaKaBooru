import { createRouter, createWebHistory } from 'vue-router'
import AtlasesView from '@/views/AtlasesView.vue'
import Default from '@/views/AtlasesView/Default.vue'
import Atlases from '@/views/AtlasesView/Atlases.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      // 重定向到 /atlases
      path: '/',
      redirect: '/atlases'
    },
    {// 图集列表
      path: '/atlases',
      component: AtlasesView,
      children: [
        { //主页
          path: '/atlases',
          name:'default',
          component: Default
        },
        { //图集列表
          path: '/atlases/:id',
          name:'atlases',
          component: Atlases
        }
      ]
    }
  ]
})

export default router
