<template>
  <div class="item-description-window">
    <div>
      <IconBookmark :item-id="item.itemId"></IconBookmark>
    </div>
    <div>
      <h2> {{ item.username }} </h2>
      <h3> {{ item.price }} kr </h3>
    </div>
    <div class="short-description">
      <h1> Description </h1><hr>
      <p>{{ item.briefDesc }}</p>
    </div>
    <div class="longer-description">
      <h1> Full Description </h1><hr>
      <p>{{ item.fullDesc }}</p>
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
      type: Object,
      required: true
    }
  },
  data(){
    return {}
  },
  setup(props) {
    console.log(props.item)
    return null
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
