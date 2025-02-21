import { createRouter, createWebHistory } from 'vue-router'
import AtlasListView from '@/views/AtlasListView.vue'
import Default from '@/views/AtlasesView/Default.vue'
import AtlasList from '@/views/AtlasesView/AtlasList.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      // 重定向到 /atlases
      path: '/',
      redirect: '/atlases',
    },
    {
      // 图集列表
      path: '/atlases',
      component: AtlasListView,
      children: [
        {
          //主页
          path: '/atlases',
          name: 'default',
          component: Default,
        },
        {
          //图集列表
          path: '/atlases/:id',
          name: 'atlases',
          component: AtlasList,
        },
      ],
    },
  ],
})

export default router
