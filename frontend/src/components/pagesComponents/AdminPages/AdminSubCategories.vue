<template>
  <div class="sub-categories-window">
    <div class="sub-categories-representation">
      <h1>Sub category</h1>
      <div class="parent-category">
        <label> hei {{ parentCategory }}</label>
      </div>
      <div>
        <ul>
          <li v-for="category in this.currentMainCategories" :key="category.categoryId " class="category-list" @click="changeCategory(category.categoryId)"> {{ category }} </li>
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
      console.log("Dette er categories FØR tømt:" + this.categories.length)
      this.currentMainCategories=[]
      console.log("Dette er categories etter tømt:" + this.categories.length)
      await this.store.fetchSubCategoriesByMainId(parentId).then(() => {
        this.categories = this.store.getSubCategories
        //this.categories.push(this.store.getSubCategories);
        for (let i = 0; i < this.categories.length; i++) {
          console.log("Dette er categories: " + this.categories.at(i).categoryName)
        }

      })
    },

  },
  setup() {
    const store = useCategoryStore();
    console.log(store.setStartList())
    this.currentMainCategories = store.setStartList()
    //let categories = [];
    // let categories = ref([]);
    //
    // onMounted(async () => {
    //   await store.fetchMainCurrentCategories();
    //
    //   categories.value = store.getCurrentCategory;
    //   console.log("getCurrentCategory: \n" + store.getCurrentCategory)
    //   // categories = computed(() => {
    //   //   console.log("getCurrentCategory: \n" + store.getCurrentCategory)
    //   //   return store.getCurrentCategory;
    //   // });
    //
    //   console.log("Dette er categories" + categories.value)
    // })

    return {
      store
    }

  },
  mounted() {
    let categories = ref([]);

    onMounted(async () => {
      await store.fetchMainCurrentCategories();

      categories.value = store.getCurrentCategory;
      console.log("getCurrentCategory: \n" + store.getCurrentCategory)
      // categories = computed(() => {
      //   console.log("getCurrentCategory: \n" + store.getCurrentCategory)
      //   return store.getCurrentCategory;
      // });

      console.log("Dette er categories" + categories.value)
    })
  },
  watch() {

  }
}
</script>

<style scoped>
  .sub-categories-window{
    display: grid;
    grid-template-rows: 2fr 1fr;
    background-color: #7EB09B;
  }
  .sub-categories-representation{
    display: grid;
  }
  .parent-category{
    border: black solid 1px;
    background-color: white;
    width: 80%;
  }
</style>