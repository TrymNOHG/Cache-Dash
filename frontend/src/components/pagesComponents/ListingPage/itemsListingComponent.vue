<template>
  <div class="itemsListing-window">
    <div v-for="item in items" key="listingView.id" class="item-list">
      <router-link :to="{name : 'itemView', params : { name: this.categoryName.toString(), id: item.itemId}}" class="link">
        <ItemThumbnail :item="item"/>
      </router-link>
    </div>
  </div>
</template>

<script>
import ItemThumbnail from "@/components/basicInputComponents/ItemThumbnail.vue";
import { useItemStore } from "@/store/store";
import {computed} from "vue";

export default {
  name: "itemsListingComponent",
  components: {ItemThumbnail},
  props: {
    categoryId: {
      type: Number,
      required: true
    },
    categoryName: {
      type: String,
      required: true
    }
  },
  data(){
    return{

    }
  },
  setup(props) {

    const store = useItemStore();
    const items = computed(() => {
      return store.getItems;
    });

    store.fetchItemsByCategoryId(props.categoryId)
    // console.log("Items: ")
    //
    // for(let item of this.items.value) {
    //   console.log(item)
    // }

    return {
      items
    }
  }

}
</script>

<style scoped>
  .itemsListing-window{
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(205px, 200px));
    justify-content: space-between;
    text-align: left;
    width: auto;
  }

  .item-list{
    width: 200px;
    height: 300px;
    margin: 5px;
  }

  .link {
    border-radius: 20px;
    border-color: #475B5A;
    background: #FFD700;
    align-content: center;
    justify-content: center;
    /*height: 300px;*/
    /*width: 230px;*/
    height: inherit;
    width: inherit;
    color: black;
    box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5),
    -2px -2px 4px rgba(255, 255, 255, 0.5),
    inset 1px 1px 2px rgba(0, 0, 0, 0.2),
    inset -1px -1px 2px rgba(255, 255, 255, 0.7);
    transform: translate(0, -1px);
    transition: all 0.1s ease-in-out;
  }

  .link:active {
    background-color: white;
    color: #1E293B;
  }

  .link:hover {
    box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
    -4px -4px 8px rgba(255, 255, 255, 0.5),
    inset 1px 1px 2px rgba(0, 0, 0, 0.2),
    inset -1px -1px 2px rgba(255, 255, 255, 0.7);
    transform: translate(0, -2px);
    color: #1E293B !important;
  }
</style>
