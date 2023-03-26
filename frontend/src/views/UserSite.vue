<template>
  <div class="userSite-window">
    <div class="buttons">
      <div class="link" @click="whatToShow(false)">Items</div>
      <div class="link" @click="whatToShow(true)">Archive</div>
    </div>
    <div class="userInformation-window">
      <my-items v-if="!pageDisplay" style="overflow-y:auto" :items="userItems"/>
      <user-archive v-else style="overflow-y:auto" items=""/>
      <personal-information/>
    </div>
  </div>
</template>

<script>
import PersonalInformation from "@/components/pagesComponents/UserPage/PersonalInformationComponent.vue";
import MyItems from "@/components/pagesComponents/UserPage/MyItemsComponent.vue";
import UserArchive from "@/components/pagesComponents/UserPage/UserArchive.vue";
import {useLoggedInStore} from "@/store/store";
import {loadListingByUser} from "@/services/ItemService";

export default {
  name: "myProfile",
  components: {UserArchive, MyItems, PersonalInformation},

  async setup() {
    const store = useLoggedInStore();
    await store.fetchUser();
    const user = store.getUser.data;
    let userItems = [];

    await loadListingByUser(user.username).then(response => {
      console.log(response.data())
      userItems = response.data;
    }).catch(err => {
      console.log(err)
    });

    console.log(userItems)

    return {
      store,
      user,
      userItems
    }
  },

  data() {
    return {
      pageDisplay: false,
    }
  },

  methods: {
    whatToShow(bool) {
      this.pageDisplay = bool;
    }
  }

}
</script>

<style scoped>

.buttons{
  display: flex;
  flex-direction: row;
  justify-content: center;
  justify-items: center;
  margin-top: 10px;
}

.link {
  text-align: center;
  border-radius: 2px;
  min-width: 200px;
  max-height: 40px;
  margin: 10px;
  background-color: #3f9293;
  color: white;
}

.link:hover {
  background-color: #55a4e4;
}

.userSite-window{
  display: grid;
  grid-template-rows: 1fr 7fr;
}

.userInformation-window {
  display: grid;
  grid-template-columns: 1fr 1fr;
}

@media (max-width: 768px) {
  .userInformation-window {
    grid-template-columns: 1fr;
    grid-template-rows: 1fr 1fr;
  }
}
</style>
