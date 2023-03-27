<template>
  <div class="itemsView-window" >
    <div class="filter-section">
      <filter-component class="filter" :categoryId=this.categoryId :categoryName=this.categoryName></filter-component>
    </div>
    <div class="searchbar-itemlisting">
      <div class="toppbar">
        <mainpage-search-bar class="search"></mainpage-search-bar>
      </div>
      <items-listing-component class="itemsListing" :categoryId=this.categoryId :categoryName=this.categoryName></items-listing-component>
    </div>
  </div>
</template>

<script>
import MainpageSearchBar from "@/components/basicInputComponents/MainpageSearchBar.vue";
import ItemsListingComponent from "@/components/pagesComponents/ListingPage/itemsListingComponent.vue";
import FilterComponent from "@/components/pagesComponents/ListingPage/filterComponent.vue";

export default {
  name: "listingView",
  components: {FilterComponent, ItemsListingComponent, MainpageSearchBar},
  props: {
    categoryName: {
      type: String,
      required: true
    },
    categoryId: {
      type: Number,
      required: true
    }
  }
}
</script>

<style scoped>
.itemsView-window{
  display: grid;
  grid-template-columns: 1fr 3fr;
  grid-gap: 5px;
}

.toppbar{
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: space-between;
  align-items: baseline;
}

.searchbar-itemlisting{
  width: inherit;
}

.search {
  width: 50%;
}

.toppbar > div {
  margin-left: 10px;
}
</style>
