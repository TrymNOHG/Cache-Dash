<template>
  <button>
    <div class="bookmark"></div>
    <p>Add to bookmarks</p>
  </button>
</template>

<script>
export default {
  name: "IconBookmark"
}
</script>

<style scoped>
  .bookmark {
    width: 40px;
    height: 100px;
    background-color: #f00;
    position: relative;
  }

  .bookmark::before,
  .bookmark::after {
    content: "";
    position: absolute;
    left: 0;
    width: 0;
    height: 0;
    border-style: solid;
  }

  .bookmark::before {
    top: 0;
    border-width: 0 20px 20px 0;
    border-color: transparent #f00 transparent transparent;
    transform: rotate(-45deg);
  }

  .bookmark::after {
    bottom: 0;
    border-width: 0 0 20px 20px;
    border-color: transparent transparent #f00 transparent;
    transform: rotate(45deg);
  }
</style>