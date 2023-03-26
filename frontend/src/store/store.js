import { defineStore } from 'pinia'
import { getUser } from "@/services/UserService"
import {loadMainCategories} from "@/services/CategoryService";
import {loadListingsByCategoryId} from "@/services/ItemService";

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
                    // const { data : {userId, username, fullName, email, birthDate, phone, picture, role}} = response
                    // this.user = {userId, username, fullName, email, birthDate, phone, picture, role}
                    this.user = response
                }).catch(error => {
                    console.warn('error', error)
                    //TODO: handle error
                })
        }
    }
});

export const useItemStore = defineStore('item', {
    state: () => ({
        item: {
            "username": "Eirik",
            "briefDesc": "Razor computer",
            "fullDesc" : null,
            "address" : "Tjome",
            "county" :  "Vestfold",
            "categoryId" : 2,
            "price" : 3000,
            "thumbnail" : null,
            "keyInfoList" : []
        },
        items: [],
        currentCategoryId: null
    }),

    getters: {
        getItems() {
            return this.items;
        }
    },

    actions: {
        async fetchItemsByCategoryId(categoryId) {
            if(this.currentCategoryId === categoryId) return this.items
            await loadListingsByCategoryId(categoryId)
                .then(response => {
                    this.items = [];

                    for(const {itemId, username, briefDesc, fullDesc,
                            address, county, categoryId, price,
                            listingStatus, thumbnail, keyInfoList} of response.data){
                        this.items.push({itemId, username, briefDesc, fullDesc,
                            address, county, categoryId, price,
                            listingStatus, thumbnail, keyInfoList})
                    }

                    this.currentCategoryId = categoryId
                }).catch(error => {
                    console.warn('error', error)
                    //TODO: handle error
                })
        }
    }
});

export const useCategoryStore = defineStore('categoryStore', {
    state: () => ({
        mainCategories: [],
        category: {
            categoryId: null,
            mainCategoryId: null,
            subCategory: "",
        },
        categoryList: [
            {categoryId: 2, mainCategoryID: 1, subCategory: "Sport"},
            {categoryId: 5, mainCategoryID: 4, subCategory: "Cars"},
        ]
    }),

    getters: {
        allCategories(){
            return this.categoryList;
        },

        allCategoryNames(){
            let categoryNames = []
            this.categoryList.forEach(category => categoryNames.push(category.subCategory))
            return categoryNames;
        },
        getMainCategories() {
            return this.mainCategories;
        }
    },

    actions: {
        async fetchMainCategories() {
            await loadMainCategories().then(response => {
                this.mainCategories = []
                for(const category of response.data) {
                    const { categoryId, categoryName, subCategories } = category
                    this.mainCategories.push({ categoryId, categoryName, subCategories })
                }
            }).catch(error => {
                console.log('error' , error)
            })
        },
        async fetchSubCategoriesByMainId(categoryId) {
            //TODO: this...
        },
        setCorrectCategory(categoryName){
            for (let i = 0; i < this.categoryList.length; i++) {
                if (this.categoryList.at(i).subCategory === categoryName){
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
            'None',
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
