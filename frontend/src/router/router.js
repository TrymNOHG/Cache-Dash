import { createRouter, createWebHistory } from 'vue-router'
import NotFound from "@/components/NotFoundComponent.vue";
import notFound from "@/components/NotFoundComponent.vue";
import SignUp from "@/views/SignUp.vue";
import simpleForm from "@/views/simpleForm.vue";
import notFoundView from "@/views/NotFoundView.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/simpleForm',
            name: 'simpleForm',
            component: simpleForm,
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


