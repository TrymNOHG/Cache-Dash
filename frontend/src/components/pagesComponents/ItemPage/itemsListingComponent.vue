<template>
  <div class="itemsListing-window">
    <div v-for="item in items" :item="item" key="item.id" class="item-list">
      <router-link :to="`category/${category.categoryName}/item/${item.itemName}`" class="link">
        <ItemThumbnail :category="category.categoryName"/>
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
      item: {},
      items: [{
        id:1,
        itemName
      }]
    }
  }
}
</script>

<style scoped>

</style>