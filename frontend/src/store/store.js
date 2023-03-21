import { defineStore } from 'pinia'

export const useLoggedInStore = defineStore('alerts', {

    state: () => ({
        isLoggedIn: false,
        token: "",
        username: "",
    }),

    getters: {
    },

    actions: {
        setLoggedIn() {
            this.isLoggedIn = true;
        },
        setToken(token){
            this.token = token;
        },
        setUsername(username){
            this.username = username;
        }
    },
})