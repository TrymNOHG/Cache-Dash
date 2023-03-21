import { createRouter, createWebHistory } from 'vue-router'
import NotFound from "@/components/NotFoundComponent.vue";
import notFound from "@/components/NotFoundComponent.vue";
import simpleForm from "@/views/simpleForm.vue";
import notFoundView from "@/views/NotFoundView.vue";
import LoginView from "@/views/LoginView.vue";
import RegisterView from "@/views/RegisterView.vue";
import mainpageView from "@/views/mainpageView.vue";
import TermsView from "@/views/TermsView.vue";
import userSite from "@/views/userSite.vue";
import newItemView from "@/views/newItemView.vue";
import categoryCard from "@/components/CategoryCard.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/simpleForm',
            name: 'simpleForm',
            component: simpleForm,
        },
        {
            path: '/terms',
            name: 'TermsOfService',
            component: TermsView
        },
        {
            path: '/newItem',
            name: 'NewItem',
            component: newItemView
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
        },
        {
            path: '/',
            name: 'mainpage',
            component: mainpageView,
        },
        {
            path: '/my-profile',
            name: 'myProfile',
            component: userSite
        },
        {
            path: '/category/:id',
            name: 'category',
            component: categoryCard
        },

    ]
})

export default router


