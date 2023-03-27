<template>
  <div>
    <ul class="container">
      <h3>My Items</h3>
      <hr>
      <li v-for="item in items" :key="item.id" class="list-item">
        <img :src='imageStore.convertImageBackToUrl(item.thumbnail)' alt="Thumbnail of item" width="100" height="100"/>
        <span class="text">{{ item.briefDesc }}</span>
        <div class="buttons" >
          <button class="listing-button" @click="changeToArchive(item)">Sell</button>
          <button class="listing-button" @click="showUpdateModal(item)">Update</button>
        </div>
        <div class="delete-button" @click="deleteItem(item)">X</div>
      </li>
    </ul>

    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <h3>Update Item</h3>
        <form @submit.prevent="updateItem()">
          <label>Brief Description:</label>
          <basic-input type="text" v-model="updatedItem.briefDesc"/><br>
          <label>Full Description:</label>
          <basic-text-area v-model="updatedItem.fullDesc"/><br>
          <label>Address:</label>
          <basic-input type="text" v-model="updatedItem.address"/><br>
          <label>County:</label>
          <basic-input type="text" v-model="updatedItem.county"/><br>
          <label>Price:</label>
          <basic-input type="text" v-model="updatedItem.price"/><br>
          <button type="submit">Update Item</button>
          <span class="close" @click="hideUpdateModal()">X</span>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import {useItemStore} from "@/store/store";
import BasicInput from "@/components/basicInputComponents/BasicInput.vue";
import BasicTextArea from "@/components/basicInputComponents/BasicTextArea.vue";

const imageStore = useItemStore();

</script>

<script>
import { useLoggedInStore } from "@/store/store";
import { deleteListing, loadListingByUser, updateListing } from "@/services/ItemService";

export default {
  name: "myItems",

  props: {
    items: Array,
  },

  data() {
    return {
      user: null,
      showModal: false,
      updatedItem: {
        briefDesc: "",
        fullDesc: "",
        address: "",
        county: "",
        price: "",
        listingStatus: "",
        thumbnail: "",
        keyInfoList: []
      }
    };
  },

  mounted() {
    const store = useLoggedInStore();
    store.fetchUser();
    this.user = store.getUser.data;
  },

  methods: {
    async changeToArchive(item) {
      const listingUpdateDTO = {
        username: this.user.username,
        itemId: item.itemId,
        briefDesc: null,
        fullDesc: null,
        address: null,
        county: null,
        price: null,
        listingStatus: "ARCHIVED",
        thumbnail: null,
        keyInfoList: null
      };

      updateListing(listingUpdateDTO);
      await this.reloadItems();
    },

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

    showUpdateModal(item) {
      this.showModal = true;
      this.updatedItem = { ...item };
    },

    async updateItem() {
      try {
        const listingUpdateDTO = {
          username: this.user.username,
          itemId: this.updatedItem.itemId,
          briefDesc: this.updatedItem.briefDesc,
          fullDesc: this.updatedItem.fullDesc,
          address: this.updatedItem.address,
          county: this.updatedItem.county,
          price: this.updatedItem.price,
          listingStatus: null,
          thumbnail: null,
          keyInfoList: null,
        };
        await updateListing(listingUpdateDTO);
        this.hideUpdateModal();
        await this.reloadItems();
      } catch (err) {
        console.log(err);
      }
    },

    hideUpdateModal() {
      this.showModal = false;
      this.updatedItem = {
        briefDesc: "",
        fullDesc: "",
        address: "",
        county: "",
        price: "",
        listingStatus: "",
        thumbnail: "",
        keyInfoList: []
      };
    }
  }
};
</script>

<style scoped>

.modal {
  display: flex;
  align-self: center;
  text-align: center;
  justify-content: center;
  background-color: #7EB09B;
}

.buttons{
  display: flex;
  flex-direction: column;
  margin-right: 20px;
}

span{
  margin-left: 15px;
}

img{
  background-color: #bababa;
  height: 100px;
  width: 100px;
}

div{
  text-align: center;
}

.container {
  background-color: white;
  display: flex;
  flex-direction: column;
  text-align: left;
}

.delete-button{
  background-color: lightgray;
  color: black;
  width: 25px;
  height: 25px;
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

.listing-button {
  margin-top: 0px;
  border-width: 2px;
  border-color: black;
  padding: 10px 20px;
  background-color: #FFD700;
  color: black;
  border-radius: 5px;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5),
  -2px -2px 4px rgba(255, 255, 255, 0.5),
  inset 1px 1px 2px rgba(0, 0, 0, 0.2),
  inset -1px -1px 2px rgba(255, 255, 255, 0.7);
  transform: translate(0, -1px);
  transition: all 0.1s ease-in-out;
}

.listing-button:active {
  background-color: white;
  color: #1E293B;
}

.listing-button:hover {
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
  -4px -4px 8px rgba(255, 255, 255, 0.5),
  inset 1px 1px 2px rgba(0, 0, 0, 0.2),
  inset -1px -1px 2px rgba(255, 255, 255, 0.7);
  transform: translate(0, -2px);
  color: white !important;
  background-color: #4c9fdb;
}

.text {
  flex: 1;
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
  position: relative;
}

.delete-button {
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

.modal {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  top: 15%;
  width: 80%;
  height: 80%;

  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);

  padding: 20px;
  border-radius: 5px;
  z-index: 100;
  display: flex;
  flex-direction: column;
}

.modal-content {
  flex-grow: 1;
  overflow-y: auto;
}

.close {
  position: absolute;
  right: 10px;

  font-size: 15px;
  font-weight: bold;
  color: #ccc;
  cursor: pointer;
}

.close:hover{
  background-color: lightgray;
  color: black;
  width: 25px;
  height: 25px;
}

form {
  display: flex;
  flex-direction: column;
}

label {
  margin-top: 10px;
  margin-bottom: 5px;
}

input, textarea {
  margin-bottom: 10px;
  padding: 5px;
}

.modal button {
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
  -4px -4px 8px rgba(255, 255, 255, 0.5),
  inset 1px 1px 2px rgba(0, 0, 0, 0.2),
  inset -1px -1px 2px rgba(255, 255, 255, 0.7);
  transform: translate(0, -2px);
  background-color: #FFD700;
  align-self: center;
  color: black;
  padding: 8px 16px;
  border: none;
  cursor: pointer;
  margin-top: 10px;
}

.modal button:hover {
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
  -4px -4px 8px rgba(255, 255, 255, 0.5),
  inset 1px 1px 2px rgba(0, 0, 0, 0.2),
  inset -1px -1px 2px rgba(255, 255, 255, 0.7);
  transform: translate(0, -2px);
  color: white !important;
  background-color: #4c9fdb;
}
</style>