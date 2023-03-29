<template>
  <div class="itemsView-window" >
    <div class="filter-section">
      <filter-component class="filter" :categoryId=this.categoryId :categoryName=this.categoryName></filter-component>
    </div>
    <div class="searchbar-itemlisting">
      <div class="toppbar">
        <mainpage-search-bar @search-term-updated="onSearchTermUpdated" class="search"></mainpage-search-bar>
      </div>
      <items-listing-component class="itemsListing" :items=items :categoryName=this.categoryName></items-listing-component>
    </div>
  </div>
</template>

<script>
import MainpageSearchBar from "@/components/basicInputComponents/MainpageSearchBar.vue";
import ItemsListingComponent from "@/components/pagesComponents/ListingPage/itemsListingComponent.vue";
import FilterComponent from "@/components/pagesComponents/ListingPage/filterComponent.vue";
import {useItemStore} from "@/store/store";
import {computed, ref, watch} from "vue";

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
  },
  data() {
    return {
      search: "",
      overThree: false
    };
  },
  methods: {
    onSearchTermUpdated(searchTerm) {
      const store = useItemStore();
      if(searchTerm.length > 3) {
        store.filterItemsBySearchTerm(searchTerm, this.categoryId);
        this.overThree = true
        console.log("1234")
      }
      else if (this.overThree){
        store.nonSyncFetchItemsByCategoryId(this.categoryId)
        this.overThree = false
      }
    },
  },
  setup(props) {

    const store = useItemStore();
    const items = computed(() => {
      return store.getItems;
    });


    store.fetchItemsByCategoryId(props.categoryId)

    return {
      items
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
