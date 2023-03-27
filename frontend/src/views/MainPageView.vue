<template>
  <div class="container">
    <mainpage-category class="category"></mainpage-category>
    <div></div>
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
    align-items: center;
    display: grid;
    margin: 0px 50px 50px;
  }

  div{
    margin: 10px;
  }

  @media (max-width: 768px) {
    .container{
      margin: 0px;
    }
  }

</style>
