<template>
  <div class="sub-categories-window">
    <div class="sub-categories-representation">
      <h1>Sub category</h1>
      <div class="parent-category">
        <label> {{ parentCategory }}</label>
      </div>
      <div class="listing">
        <ul>
          <li v-for="category in this.currentMainCategories" :key="category.categoryId " class="category-list" @click="changeCategory(category.categoryId)"> {{ category.categoryName }} </li>
        </ul>
      </div>
    </div>
    <div class="new-category-button">
      <h2>Add new main category</h2>
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
import {useCategoryStore} from "@/store/store";
import {computed, onMounted, ref, watch} from "vue";

export default {
  name: "AdminSubCategories",

  data(){
    return{
      currentMainCategories: []
    }
  },

  methods: {
    async changeCategory(parentId){
      console.log("Dette er parent ID " + parentId)

      this.currentMainCategories=[]
      await this.store.fetchSubCategoriesByMainId(parentId).then(() => {
        this.categories = this.store.getSubCategories
        //this.categories.push(this.store.getSubCategories);

      })
    },
  },

  setup() {
    const store = useCategoryStore()

    return {
      store
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

}
ul{
  display: grid;
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
    height: 100%;

  }
  .listing{
    background-color: white;
    border: black solid 2px;
    margin: 10px;
  }
</style>
