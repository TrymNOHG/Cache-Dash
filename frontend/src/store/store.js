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
        }
    },
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
