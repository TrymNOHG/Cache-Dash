import { createRouter, createWebHistory } from 'vue-router'
import NotFound from "@/components/basicInputComponents/NotFoundComponent.vue";
import notFound from "@/components/basicInputComponents/NotFoundComponent.vue";
import simpleForm from "@/views/SimpleForm.vue";
import notFoundView from "@/views/NotFoundView.vue";
import LoginView from "@/views/LoginView.vue";
import RegisterView from "@/views/RegisterView.vue";
import mainpageView from "@/views/MainPageView.vue";
import TermsView from "@/views/TermsView.vue";
import userSite from "@/views/UserSite.vue";
import newItemView from "@/views/NewItemView.vue";
import categoryCard from "@/components/pagesComponents/MainPage/CategoryCardComponent.vue";
import listingView from "@/views/ListingView.vue";
import itemView from "@/views/ItemView.vue";
import ChatView from "@/views/ChatView.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/simpleForm',
            name: 'simpleForm',
            component: simpleForm,
        },
        {
            path: '/register/terms',
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
            path: '/category/:categoryName+_:categoryId',
            name: 'listingView',
            component: listingView,
            props: true
        },
        {
            path: '/category/:name/item/:id',
            name: 'item',
            component: itemView

        },
        {
            path: '/chat',
            name: 'chat',
            component: ChatView
        }

    ]
})

export default router


