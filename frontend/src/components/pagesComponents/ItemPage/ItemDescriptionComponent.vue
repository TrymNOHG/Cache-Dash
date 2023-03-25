<template>
  <div class="item-description-window">
    <div>
      <IconBookmark></IconBookmark>
    </div>
    <div>
      <h2> {{ '#item namn' }} </h2>
      <h3> {{'item cost'}} </h3>
    </div>
    <div class="short-description">
      <p>#short description</p>
    </div>
    <div class="longer-description">
      <p>#long description</p>
    </div>
  </div>
</template>

<script>
import IconBookmark from "@/components/icons/IconBookmark.vue";
import {array} from "yup";

export default {
  name: "ItemDescription",
  components: {IconBookmark},
  props:{
    item:{
      id: null,
      categoryName: '',
      itemName:'',
      cost:'',

    },
    items:{
      type: array,
    }
  },
  data(){
    return {
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
      ]
    }
  }
}
</script>

<style scoped>
  .item-description-window{
    display: grid;
    grid-template-rows: 1fr 1fr 2fr 4fr;
    grid-gap: 10px;
  }
  .short-description{
    border: #181818 solid 3px;
  }
  .longer-description{
    border: #181818 solid 3px;

  }
</style>