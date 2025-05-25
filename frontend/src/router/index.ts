import { createRouter, createWebHistory } from 'vue-router'
import GalleryView from '@/views/GalleryView.vue'
import TagView from '@/views/TagView.vue'
import SettingView from '@/views/SettingView.vue'

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
      component: GalleryView,
      meta: {
        title: '主页',
      },
    },
    {
      path: '/tags',
      name: 'tags',
      component: TagView,
      meta: {
        title: '标签',
      },
    },
    {
      path: '/setting',
      name: 'setting',
      component: SettingView,
      meta: {
        title: '设置',
      },
    },
  ],
})
