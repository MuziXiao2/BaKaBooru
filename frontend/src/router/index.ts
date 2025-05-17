import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/views/Layout.vue'
import Gallery from '@/views/Gallery.vue'
import Videos from '@/views/Videos.vue'
import Voices from '@/views/Voices.vue'

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
        {
          path: '/videos',
          name: 'Videos',
          component: Videos,
        },
        {
          path: '/voices',
          name: 'Voices',
          component: Voices,
        },
      ],
    },
  ],
})
