import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import 'element-plus/theme-chalk/dark/css-vars.css'
import './style.css'
import MasonryWall from '@yeger/vue-masonry-wall'

//注册App
const app = createApp(App)
app.use(router)
app.use(createPinia())
app.use(MasonryWall)
app.mount('#app')

//全局禁用默认右键菜单
document.addEventListener('contextmenu', (e) => {
  e.preventDefault()
})
