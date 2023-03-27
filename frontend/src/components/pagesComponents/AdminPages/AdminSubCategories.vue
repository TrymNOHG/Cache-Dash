<template>
  <div class="sub-categories-window">
    <div class="sub-categories-representation">
      <h1>Sub category</h1>
      <div class="parent-category">
        <label class="parentLabel"> {{ parentCategory.categoryName }}</label>
      </div>
      <div class="listing">
        <ul>
          <li v-for="category in this.currentMainCategories" :key="category.categoryId " class="category-list" @click="changeCategory(category)"> {{ category.categoryName }} </li>
        </ul>
      </div>
    </div>
    <div class="new-category-button">
      <h2>Add new category</h2>
      <div class="new-category-field">
        <label>Category name</label>
        <BasicInput
            modelvalue="text"
            v-model="newCategoryName"
            class="input-field"
        />
        <button class="button-add-catgory" @click="addNewCategory">Add category</button>
      </div>
    </div>
  </div>
</template>

<script>
import {useCategoryStore, useLoggedInStore} from "@/store/store";
import {computed, onMounted, ref, watch} from "vue";
import BasicInput from "@/components/basicInputComponents/BasicInput.vue";
import {addCategory} from "@/services/CategoryService";

export default {
  name: "AdminSubCategories",
  components: {BasicInput},

  data(){
    return{
      parentCategory:'',
      currentMainCategories: [],
      newCategoryName:''
    }
  },

  methods: {
    async changeCategory(parent){
      console.log("Dette er parent ID " + parent.categoryId)
      this.parentCategory = parent
      this.currentMainCategories=[]
      //this.currentMainCategories = (await this.store.getSubCategories)(parent.categoryId)

      await this.store.fetchSubs(parent.categoryId).then(async () => {
        this.currentMainCategories = (await this.store.getSubCategories)
        console.log(this.currentMainCategories)
        //this.categories.push(this.store.getSubCategories);

      })


    },
    async addNewCategory(){
      if(!(this.parentCategory === '' && this.newCategoryName === ''))
      //const userId = this.userStore.getUser.data.userId
      await this.store.addNewCategory(this.userStore.getUser.data.userId, this.parentCategory.categoryId, this.newCategoryName)
    }
  },

  setup() {
    const store = useCategoryStore()
    const userStore = useLoggedInStore()

    return {
      store,
      userStore
    }
  },
  mounted() {
    const categoryStore = useCategoryStore();
    categoryStore.fetchMainCategories().then(() => {
      this.currentMainCategories = categoryStore.getMainCategories;

    });
  },
  watch() {

  }
}
</script>


<style>

li{
  display: block;
}
ul{
  display: grid;
}

  .category-list{
    display: block;
  }

  .parentLabel{
    min-height: 100px;
  }

  .new-category-field{
    display: grid;
    grid-gap: 5px;
    margin: 5px;
  }

  .sub-categories-window{
    display: grid;
    grid-template-rows: 2fr 1fr;
    background-color: #7EB09B;
  }
  .sub-categories-representation{
    display: grid;
    grid-gap: 5px;
  }
  .parent-category{
    border: black solid 1px;
    background-color: white;
    margin: 10px;


  }
  .listing{
    background-color: white;
    border: black solid 2px;
    margin: 10px;
  }

button {
  border-width: 2px;
  border-color: black;
  padding: 10px 20px;
  background-color: #FFD700;
  color: black;
  margin: 10px;
  border-radius: 5px;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5),
  -2px -2px 4px rgba(255, 255, 255, 0.5),
  inset 1px 1px 2px rgba(0, 0, 0, 0.2),
  inset -1px -1px 2px rgba(255, 255, 255, 0.7);
  transform: translate(0, -1px);
  transition: all 0.1s ease-in-out;
}

button:active {
  background-color: white;
  color: #1E293B;
}

button:hover {
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
  -4px -4px 8px rgba(255, 255, 255, 0.5),
  inset 1px 1px 2px rgba(0, 0, 0, 0.2),
  inset -1px -1px 2px rgba(255, 255, 255, 0.7);
  transform: translate(0, -2px);
  color: white !important;
}
</style>
