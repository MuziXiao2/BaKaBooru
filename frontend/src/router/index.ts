import { createRouter, createWebHistory } from 'vue-router'
import Gallery from '@/views/Gallery.vue'
import Tags from '@/views/Tags.vue'
import Setting from '@/views/Setting.vue'

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '',
      redirect: '/gallery',
    },
    {
      path: '/gallery',
      name: 'gallery',
      component: Gallery,
      meta: {
        title: '主页',
      },
    },
    {
      path: '/tags',
      name: 'tags',
      component: Tags,
      meta: {
        title: '标签',
      },
    },
    {
      path: '/setting',
      name: 'setting',
      component: Setting,
      meta: {
        title: '设置',
      },
    },
  ],
})
