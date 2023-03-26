<template>
  <div class="catergory-window">
    <div class="label">
      <h2>Category</h2>
    </div>
    <div class="category-container">
      <div v-for="category in mainCategories" :category="category" key="category.categoryId" class="category-list">
        <router-link :to="{ name : 'listingView', params: { categoryName : category.categoryName, categoryId: category.categoryId}}" class="link">
          <CategoryCard :category="category.categoryName"/>
        </router-link>
      </div>
    </div>

  </div>
</template>

<script>
import CategoryCard from "@/components/pagesComponents/MainPage/CategoryCardComponent.vue";
import { useCategoryStore } from "@/store/store";
import {computed} from "vue";

export default {
  name: "mainpageCategory",
  components: {
    CategoryCard
  },
  data(){
    return{
      category: {},
      // categories: [{
      //   id:1,
      //   categoryName:"Cars",
      //
      // },
      // {
      // id:2,
      // categoryName: "Guns",
      // },
      //   {
      //     id:3,
      //     categoryName: "Humans",
      //   },
      //
      // ]
    }
  },
  setup() {

    const store = useCategoryStore();
    const mainCategories = computed(() => {
      return store.getMainCategories;
    });

    store.fetchMainCategories()

    // watch(mainCategories, (newMainCategories) => {
    //   console.log(newMainCategories)
    //   this.mainCategories = newMainCategories;
    // });

    return {
      mainCategories
    }
  }
}
</script>

<style scoped>

  .catergory-window{
    display: grid;
    grid-template-rows: 1fr 4fr;
  }

  .category-list{
    display: inline-block;
    justify-content: space-between;
  }

  .category-container {
    background-color: #7EB09B;
    text-align: left;
  }

  .link{
    border-radius: 50%;
    width: 150px;
    height: 150px;
    background: #FFD700;
    color: black;
    box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5),
    -2px -2px 4px rgba(255, 255, 255, 0.5),
    inset 1px 1px 2px rgba(0, 0, 0, 0.2),
    inset -1px -1px 2px rgba(255, 255, 255, 0.7);
    transform: translate(0, -1px);
    transition: all 0.1s ease-in-out;

  }
  .link:active {
    background-color: white;
    color: #1E293B;
  }

  .link:hover {
    box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
    -4px -4px 8px rgba(255, 255, 255, 0.5),
    inset 1px 1px 2px rgba(0, 0, 0, 0.2),
    inset -1px -1px 2px rgba(255, 255, 255, 0.7);
    transform: translate(0, -2px);
    color: #1E293B !important;
  }

  .label{
    background-color: white;
    text-align: left;
    font-family: sans-serif;
    font-size: xx-large;
  }
</style>
