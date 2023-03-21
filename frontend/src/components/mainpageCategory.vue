<template>
  <div class="catergory-window">
    <div class="label">
      <h2>Category</h2>
    </div>
    <div class="category-container">
      <div v-for="category in categories" :category="category" key="category.id" class="category-list">
        <router-link :to="`/category/${category.id}`" class="link">
          <CategoryCard :category="category.categoryName"/>
        </router-link>
      </div>
    </div>

  </div>
</template>

<script>
import CategoryCard from "@/components/CategoryCard.vue";
export default {
  name: "mainpageCategory",
  components: {
    CategoryCard
  },
  data(){
    return{
      category: {},
      categories: [{
        id:1,
        categoryName:"Cars",

      },
      {
      id:2,
      categoryName: "Guns",
      },
        {
          id:3,
          categoryName: "Humans",
        },

      ]
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
    opacity: 70%;
    text-align: left;
  }

  .link{
    border-radius: 50%;
    width: 100px;
    height: 100px;
    background: #FFD700;
    align-content: center;
    justify-content: center;
  }
  .label{
    background-color: white;
    text-align: left;
    font-family: sans-serif;
    font-size: xx-large;
  }
</style>