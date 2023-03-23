<template>
  <div class="itemsView-window" >
    <div class="filter-section">
      <filter-component class="filter"></filter-component>
    </div>
    <div class="searchbar-itemlisting">
      <mainpage-search-bar class="search"></mainpage-search-bar>
      <items-listing-component class="itemsListing"></items-listing-component>
    </div>
  </div>
</template>

<script>
import MainpageSearchBar from "@/components/basicInputComponents/mainpageSearchBar.vue";
import ItemsListingComponent from "@/components/pagesComponents/ListingPage/itemsListingComponent.vue";
import FilterComponent from "@/components/pagesComponents/ListingPage/filterComponent.vue";

export default {
  name: "listingView",
  components: {FilterComponent, ItemsListingComponent, MainpageSearchBar}
}
</script>

<style scoped>
  .itemsView-window{
    display: grid;
    grid-template-columns: 1fr 3fr;
    grid-gap: 5px;
  }


  .filter{


  }
  .searchbar-itemlisting{
    width: inherit;

  }
</style>