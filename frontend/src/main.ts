import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

//注册App
const app = createApp(App)
app.use(router)
app.use(createPinia())
app.mount('#app')

//全局禁用默认右键菜单
document.addEventListener('contextmenu', (e) => {
  e.preventDefault()
})
