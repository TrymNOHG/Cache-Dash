<template>
  <ul class="container">
    <h3>My Archive</h3>
    <hr>
    <li v-for="item in items" :key="item.id" class="list-item">
      <img :src='store.convertImageBackToUrl(item.thumbnail)' alt="Thumbnail of item" width="100" height="100"/>
      <span class="text">{{ item.briefDesc }}</span>
      <RouterLink to="">Go to</RouterLink>
      <div class="delete-button" @click="deleteItem(item)">X</div>
    </li>
  </ul>
</template>

<script setup>
import {useItemStore} from "@/store/store";

const store = useItemStore();


</script>

<script>
import {deleteListing, loadListingByUser} from "@/services/ItemService";

export default {
  name: "BookmarkComponent",

  props: {
    items: Array,
  },

  methods: {

    async reloadItems() {
      try {
        const response = await loadListingByUser(this.user.username);
        this.items = response.data;
      } catch (err) {
        console.log(err);
      }
    },

    async deleteItem(item) {
      const listingDeletionDTO = {
        username: this.user.username,
        itemId: item.itemId
      };
      await deleteListing(listingDeletionDTO);
      await this.reloadItems();
    },

  }
}
</script>


<style scoped>

span{
  margin-left: 15px;
}

img{
  background-color: #bababa;
  height: 100px;
  width: 100px;
}

.container {
  background-color: white;
  display: flex;
  flex-direction: column;
  text-align: left;
}

.list-item {
  color: blue;
  background-color: white;
  border: black 2px solid;
  box-shadow: 0 3px 10px rgb(0 0 0 / 0.2);
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
  padding: 10px;
}

.list-item:hover {
  background-color: white;
}

.text {
  flex: 1;
}

.delete-button {
  text-align: center;
  position: absolute;
  top: 0;
  right: 0;
  background-color: white;
  color: black;
  width: 25px;
  height: 25px;
  border-radius: 5px;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
  -4px -4px 8px rgba(255, 255, 255, 0.5),
  inset 1px 1px 2px rgba(0, 0, 0, 0.2),
  inset -1px -1px 2px rgba(255, 255, 255, 0.7);
}

.delete-button:hover {
  position: absolute;
  top: 0;
  right: 0;
  background-color: lightgray;
  color: black;
  width: 25px;
  height: 25px;
  border-radius: 5px;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
  -4px -4px 8px rgba(255, 255, 255, 0.5),
  inset 1px 1px 2px rgba(0, 0, 0, 0.2),
  inset -1px -1px 2px rgba(255, 255, 255, 0.7);
}
</style>