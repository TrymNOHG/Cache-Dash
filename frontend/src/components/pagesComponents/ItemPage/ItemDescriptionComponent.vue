<template>
  <div class="item-description-window">
    <div>
      <IconBookmark></IconBookmark>
    </div>
    <div>
      <h2> halo{{}} </h2>
      <h3> hei{{}} </h3>
    </div>
    <div class="short-description">
      <p></p>
    </div>
    <div class="longer-description">
      <p></p>
    </div>
  </div>
</template>

<script>
import IconBookmark from "@/components/icons/IconBookmark.vue";

export default {
  name: "ItemDescription",
  components: {IconBookmark},
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
    background: #FFD700;
  }
</style>