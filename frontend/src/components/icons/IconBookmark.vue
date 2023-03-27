<template>
  <button class="bookmark" @click="addToBookmark">
    <div class="a1">
      <!--      <img src="/src/assets/bookmark.png" class="bookmark-tag">-->
    </div>
    <div class="a2">
      <label class="bookmark-text">Add to bookmarks</label>
    </div>
  </button>
</template>

<script>
import { useLoggedInStore } from "@/store/store"
import { saveBookmark } from "@/services/BookmarkService"
export default {
  name: "IconBookmark",
  props: {
    itemId: {
      type: Number,
      required: true
    }
  },
  methods: {
    async addToBookmark() {
      const store = useLoggedInStore()
      if(!store.isLoggedIn) {
        alert("Remember to login before saving bookmarks!");
        await this.$router.push("/")
      } else {
        await store.fetchUser()
        const username = store.getUser.data.username
        const bookmarkDTO = {
          itemId: this.itemId,
          username: username
        }

        console.log("Saving bookmark using: " + bookmarkDTO.username + ", " + bookmarkDTO.itemId)
        saveBookmark(bookmarkDTO).then(response => {
          console.log("Bookmark was successfully saved!")
        }).catch(error => {
          console.warn('Error', error)
          alert("You have already bookmarked this item!")
        })
      }

    }
  },
  setup() {
    const store = useLoggedInStore;

    return {
      store
    }
  }
}
</script>

<style scoped>
.bookmark {
  display: grid;
  width: 40%;
  height: 70%;
  background-color: rgba(203, 202, 202, 0.96);

}
.bookmark-tag{
  width: 100%;
  height: 100%;
}

.bookmark-text{
  font-size: 100%;
  padding: 2%;
}
.a1{
  grid-column: 1;
}
.a2{
  grid-column: 2;
}



</style>
