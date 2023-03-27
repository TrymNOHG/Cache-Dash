import { createApp } from 'vue'
import { createPinia } from 'pinia'
import piniaPersist from 'pinia-plugin-persist'
import App from './App.vue'
import './assets/main.css'
import router from "@/router/router";

/* Icons imports */
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faUserCircle } from '@fortawesome/free-solid-svg-icons'

/* Imports fr multiple languages */
import i18n from "@/locales/i18n";

/* add icons to the library */
library.add(faUserCircle)

const pinia = createPinia()
pinia.use(piniaPersist)


createApp(App)
    .use(pinia)
    .use(router)
    .use(i18n)
    .component('font-awesome-icon', FontAwesomeIcon)
    .mount('#app');
