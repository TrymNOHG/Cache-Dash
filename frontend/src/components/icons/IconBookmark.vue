<template>
  <button class="bookmark" @click="">
    <div class="a1">
<!--      <img src="/src/assets/bookmark.png" class="bookmark-tag">-->
    </div>
    <div class="a2">
      <label class="bookmark-text">Add to bookmarks</label>
    </div>
  </button>
</template>

<script>
export default {
  name: "IconBookmark",
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