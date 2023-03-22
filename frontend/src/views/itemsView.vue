<template>
  <div>
    <mainpage-search-bar class="search"></mainpage-search-bar>
    <items-listing-component class="itemsListing"></items-listing-component>

  </div>
</template>

<script>
import MainpageSearchBar from "@/components/basicInputComponents/mainpageSearchBar.vue";
import ItemsListingComponent from "@/components/pagesComponents/ItemPage/itemsListingComponent.vue";

export default {
  name: "itemsView",
  components: {ItemsListingComponent, MainpageSearchBar}
}
</script>

<style scoped>

</style>