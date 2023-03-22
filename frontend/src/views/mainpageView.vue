<template>
  <div class="container">
    <mainpage-search-bar class="search"></mainpage-search-bar>
    <mainpage-category class="category">hei</mainpage-category>
    <MainpageRecentlyAdded class="recAdded">hei</MainpageRecentlyAdded>
  </div>
</template>

<script>
import MainpageRecentlyAdded from "@/components/pagesComponents/MainPage/mainpageRecentlyAdded.vue";
import MainpageSearchBar from "@/components/basicInputComponents/mainpageSearchBar.vue";
import MainpageCategory from "@/components/pagesComponents/MainPage/mainpageCategory.vue";
export default {
  name: "mainpageView",
  components: {MainpageCategory, MainpageSearchBar, MainpageRecentlyAdded}
}

</script>

<style scoped>

  .container{
    display: grid;
    grid-template-columns: 1fr 4fr 4fr 1fr;
    row-gap: 10px;
    grid-template-rows: 1fr 4fr 4fr;
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
  }

</style>