import { defineStore } from 'pinia'
import { getUser } from "@/services/UserService"
import {loadAllCategories, loadMainCategories} from "@/services/CategoryService";
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
        categoryList: [],
        currentCategory : []
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
        },
        getCurrentCategory() {
            console.log("getCurrentCategory" + this.currentCategory)
            return this.currentCategory;
        },
        getSubCategories() {
            return this.categoryList;
        }
    },
    setters: {
        setCurrentCategoryList(newList) {
            this.currentCategory = newList
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

        async fetchMainCurrentCategories() {
            await loadMainCategories().then(response => {
                this.currentCategory = []
                for(const category of response.data) {
                    console.log("Dette er respons i forloop:" + category.categoryName)
                    const { categoryId, categoryName, subCategories } = category
                    this.currentCategory.push({ categoryId, categoryName, subCategories })
                    console.log("fetchMainCurrentCategories end" + this.currentCategory)
                }
            }).catch(error => {
                console.log('error' , error)
            })
        },

        async fetchSubCategoriesByMainId(mainCategoryId) {
            console.log("mainCategoryId")
            await loadAllCategories(mainCategoryId).then(response => {
                this.categoryList = []
                const { categoryId, categoryName, subCategories } = response.data
                this.categoryList.push({ categoryId, categoryName, "mainCategoryId" : null })
                console.log(response)
                for(const category of subCategories) {
                    const { categoryId, categoryName, subCategories} = category
                    if(categoryId === mainCategoryId){
                        this.categoryList.push({ categoryId, categoryName, subCategories })
                        console.log(categoryName)
                    }
                }
            })
        },

        setCorrectCategory(categoryName){
            for (let i = 0; i < this.categoryList.length; i++) {
                if (this.categoryList.at(i).subCategory === categoryName){
                    this.category = this.categoryList.at(i);
                }
            }
        },

        async setStartList(){
            await this.fetchMainCategories().then(() => {
                this.currentCategory = this.mainCategories
            })
            console.log("setStartList" + this.currentCategory)
            return this.currentCategory
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
