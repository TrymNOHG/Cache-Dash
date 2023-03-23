import { defineStore } from 'pinia'

export const useLoggedInStore = defineStore('alerts', {

    state: () => ({
        user: {
            loggedIn: false,
            token: "",
            username: "",
            fullName:"",
            email:"",
            dateOfBirth:"",
            phoneNumber:"",
            role: "",
        },
    }),

    getters: {
        isLoggedIn(){
            return this.user.loggedIn;
        }
    },

    actions: {
        setUser(user){
            this.user = user;
            console.log(this.user)
        }
    },
});
