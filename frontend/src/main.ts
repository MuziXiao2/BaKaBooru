import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')

//全局禁用默认右键菜单
document.addEventListener('contextmenu', (e) => {
  e.preventDefault()
})
