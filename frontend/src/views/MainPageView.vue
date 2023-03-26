<template>
  <div class="container">
    <mainpage-search-bar class="search"></mainpage-search-bar>
    <mainpage-category class="category"></mainpage-category>
    <MainpageRecentlyAdded class="recAdded"></MainpageRecentlyAdded>
  </div>
</template>

<script>
import MainpageRecentlyAdded from "@/components/pagesComponents/MainPage/mainpageRecentlyAddedComponent.vue";
import MainpageSearchBar from "@/components/basicInputComponents/MainpageSearchBar.vue";
import MainpageCategory from "@/components/pagesComponents/MainPage/mainpageCategoryComponent.vue";
export default {
  name: "mainpage",
  components: {MainpageCategory, MainpageSearchBar, MainpageRecentlyAdded}
}

</script>

<style scoped>

  .container{
    display: grid;
    grid-template-columns: 1fr 4fr 4fr 1fr;
    row-gap: 10px;
    grid-template-rows: 1fr 3fr 3fr;
  }
  .search{
    grid-column: 2 / 3;
    grid-row: 1;
  }
  .category{
    grid-column: 2 / 4;

  }
  .recAdded{
    grid-column: 2 / 4;
    padding-bottom: 15px;
  }

</style>
