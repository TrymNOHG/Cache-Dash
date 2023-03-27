<template>
  <div class="catergory-window">
    <div class="label">
      <h1>Main Category</h1>
    </div>
    <div class="category-container">
      <div v-for="category in this.mainCategories" :category="category" key="category.categoryId" class="category-list">
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
  </div>
</template>

<script>
import CategoryCard from "@/components/pagesComponents/MainPage/CategoryCardComponent.vue";
import { useCategoryStore } from "@/store/store";
import {computed} from "vue";
import BasicInput from "@/components/basicInputComponents/BasicInput.vue";

export default {
  name: "AdminMainCategories",
  components: {BasicInput, CategoryCard},
  data() {
    return {
      category: {},
      newCategoryName:'',
    }
  },
  setup() {
    const store = useCategoryStore()
    store.fetchMainCategories()


    const mainCategories = computed( () => {
      return store.getMainCategories.data
    })


    console.log(mainCategories)

    return {
      mainCategories
    }

  },
  methods: {
    addNewCategory(){
      const newCategory = this.newCategoryName
      /*
      TODO: Add function for adding a main category
      */
    },
  }
}
</script>

<style scoped>
  .catergory-window{
    display: grid;
  }
  .new-category-field{
    display: grid;
    grid-gap: 10px;
  }

  input,
  textarea {
    padding: 10px;
    border-radius: 5px;
    border: 2px solid #ccc;

    font-size: 1rem;
    width: 100%;
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
