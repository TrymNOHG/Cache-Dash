import { createRouter, createWebHistory } from 'vue-router'
import NotFound from "@/components/NotFoundComponent.vue";
import notFound from "@/components/NotFoundComponent.vue";
import simpleForm from "@/views/simpleForm.vue";
import notFoundView from "@/views/NotFoundView.vue";
import LoginView from "@/views/LoginView.vue";
import RegisterView from "@/views/RegisterView.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/simpleForm',
            name: 'simpleForm',
            component: simpleForm,
        },
        {
            path: '/login',
            name: 'login',
            component: LoginView
        },
        {
            path: '/register',
            name: 'register',
            component: RegisterView
        },
        {
          path: '/notFound',
          name: 'NotFound',
          component: notFoundView
        },
        {
            path: '/:catchAll(.*)',
            name: 'NotFound',
            component: notFound

        },
        {
            path: '/404/:resource',
            name: '404Resource',
            component: NotFound,
            props: true
        }

    ]
})

export default router


