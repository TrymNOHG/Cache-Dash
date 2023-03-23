<template>
  <div class="itemsListing-window">
    <div v-for="item in items" :item="item" key="listingView.id" class="item-list">
      <router-link :to="`${item.categoryName}/item/${item.id}`" class="link">
        <ItemThumbnail :category="item.categoryName"/>
      </router-link>
    </div>
  </div>
</template>

<script>
import ItemThumbnail from "@/components/basicInputComponents/ItemThumbnail.vue";

export default {
  name: "itemsListingComponent",
  components: {ItemThumbnail},
  data(){
    return{
      item: {
      },
      items: [{
        id:1,
        categoryName: 'Cars',
        itemName: 'Selling car'
      },
      {
        id:2,
        categoryName: 'Cars',
        itemName: 'Selling Mercedes'
      },
      {
        id:3,
        categoryName: 'Cars',
        itemName: 'Selling Mercedes'
      },
        {
          id:4,
          categoryName: 'Cars',
          itemName: 'Selling Mercedes'
        },
        {
          id:5,
          categoryName: 'Cars',
          itemName: 'Selling Mercedes'
        },

      ]
    }
  },
  /*props:{
    item:{
      id:'',
      categoryName:'',
      itemName:'',
      cost:''

    },
    items:[

    ]
  }
   */
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