import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/views/Layout.vue'
import Gallery from '@/views/Gallery.vue'

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: Layout,
      children: [
        {
          path: '',
          redirect: '/gallery',
        },
        {
          path: '/gallery',
          name: 'Gallery',
          component: Gallery,
        },
      ],
    },
  ],
})
