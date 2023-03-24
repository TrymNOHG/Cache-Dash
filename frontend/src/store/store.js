import { defineStore } from 'pinia'
import { getUser } from "@/services/UserService"

export const useLoggedInStore = defineStore('user', {


    state: () => ({
        sessionToken: null,
        user: {
            userId: null,
            username: null,
            fullName: null,
            email: null,
            birthDate: null,
            phone: null,
            picture: [],
            role: null,
        },
    }),

    getters: {
        isLoggedIn(){
            return this.sessionToken !== null;
        },
        getUser() {
            return this.user;
        },
        getSessionToken() {
            return this.sessionToken;
        }
    },

    actions: {
        setSessionToken(sessionToken) {
            this.sessionToken = sessionToken
        },
        async fetchUser() {
            await getUser()
                .then(response => {
                    const { data : {userId, username, fullName, email, birthDate, phone, picture, role}} = response
                    this.user = {userId, username, fullName, email, birthDate, phone, picture, role}
                    console.log("Response:")
                    console.log(response.data)
                    this.user = response
                }).catch(error => {
                    console.warn('error', error)
                    //TODO: handle error
                })
        }
    }
});

export const useCategoryStore = defineStore('categoryStore', {
    state: () => ({
        category: {
            categoryID: null,
            mainCategoryID: null,
            categoryName: "",
        },
        categoryList: [
            {categoryID: 2, mainCategoryID: 1, categoryName: "Sport"},
            {categoryID: 3, mainCategoryID: 1, categoryName: "Cars"},
        ]
    }),

    getters: {
        allCategories(){
            return this.categoryList;
        },

        allCategoryNames(){
          let categoryNames = []
          this.categoryList.forEach(category => categoryNames.push(category.categoryName))
          return categoryNames;
        }
    },

    actions: {
        setCorrectCategory(categoryName){
            for (let i = 0; i < this.categoryList.length; i++) {
                if (this.categoryList.at(i).categoryName === categoryName){
                    this.category = this.categoryList.at(i);
                }
            }
        }
    }
});


export const useCountyStore = defineStore('countyStore', {
    state: () => ({
        county: {
            countyName: "",
        },
        categoryList: [
            'Troms og Finnmark',
            'Nordland',
            'Trøndelag',
            'Møre og Romsdal',
            'Vestland',
            'Rogaland',
            'Agder',
            'Vestfold og Telemark',
            'Viken',
            'Oslo',
            'Innlandet'
        ]
    }),

    getters: {
        allCounties(){
            return this.categoryList;
        },
    },

    actions: {
        setSelected(county){
            this.county = county;
        }
    }
});

export const useImageStore = defineStore('imageStore', {
    state: () => ({
        imageToSend: []
    }),

    getters: {
        test(){
            return this.imageToSend.at(0);
        }
    },

    actions: {
        addImage(newImage){
            this.imageToSend.unshift(newImage)
        }
    }
});
