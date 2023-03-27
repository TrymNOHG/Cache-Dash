<template>
  <div class="userSite-window">
    <div class="buttons">
      <div class="link" @click="whatToShow(1)">{{ $t('items') }}</div>
      <div class="link" @click="whatToShow(2)">{{ $t('archive') }}</div>
      <div class="link" @click="whatToShow(3)">{{ $t('bookmarks') }}</div>
    </div>
    <div class="userInformation-window">
      <personal-information/>
      <div v-if="pageDisplay === 1">
        <my-items style="overflow-y:auto" :items="nonArchivedItems" />
      </div>
      <div v-else-if="pageDisplay === 2">
        <user-archive style="overflow-y:auto" :items="archivedItems"/>
      </div>
      <div v-else-if="pageDisplay === 3">
        <bookmark-component style="overflow-y:auto" :items="bookmarkedItems"/>
      </div>
    </div>
  </div>
</template>

<script>
import PersonalInformation from "@/components/pagesComponents/UserPage/PersonalInformationComponent.vue";
import MyItems from "@/components/pagesComponents/UserPage/MyItemsComponent.vue";
import UserArchive from "@/components/pagesComponents/UserPage/UserArchive.vue";
import {useLoggedInStore} from "@/store/store";
import {loadListingByUser} from "@/services/ItemService";
import { ref, computed } from 'vue';
import BookmarkComponent from "@/components/pagesComponents/UserPage/BookmarkComponent.vue";


export default {
  name: "myProfile",
  components: {BookmarkComponent, UserArchive, MyItems, PersonalInformation},


  setup() {
    const store = useLoggedInStore();
    store.fetchUser();
    const user = store.getUser.data;
    const userItems = ref([]);

    async function loadItems() {
      try {
        const response = await loadListingByUser(user.username);
        userItems.value = response.data; // update the userItems with the response data
        console.log(userItems.value);
      } catch (err) {
        console.log(err);
      }
    }

    loadItems();

    // filter out archived items
    const nonArchivedItems = computed(() => {
      return userItems.value.filter(item => item.listingStatus !== 'ARCHIVED' );
    });

    // filter out non-archived items
    const archivedItems = computed(() => {
      return userItems.value.filter(item => item.listingStatus === 'ARCHIVED');
    });

    // filter out bookmarked items
    const bookmarkedItems = computed(() => {
      return userItems.value.filter(item => item.listingStatus !== 'ARCHIVED' );
    });

    return {
      store,
      user,
      userItems,
      nonArchivedItems,
      archivedItems,
      bookmarkedItems
    };
  },

  data() {
    return {
      pageDisplay: null,
    }
  },

  methods: {
    whatToShow(numb) {
      this.pageDisplay = numb;
    },
  },
};
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
  min-width: 120px;
  max-height: 40px;
  margin: 5px;
  background-color: #3f9293;
  color: white;
  font-size: 0.9rem;
}

.link:hover {
  background-color: #55a4e4;
}

.userInformation-window {
  display: grid;
  grid-template-columns: 1fr;
  margin: 10px;
  max-height: 600px;
}

.userSite-window{
  display: grid;
  grid-template-rows: 1fr 7fr;
  overflow-y: auto;
}

@media (min-width: 768px) {
  .userInformation-window {
    grid-template-columns: 1fr 1fr;
    max-height: none;
  }
}

</style>