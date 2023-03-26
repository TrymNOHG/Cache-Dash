<template>
  <div class="thumbnail">
    <figure class="picture">
      <img :src='item.thumbnail' alt="Thumbnail of item" width="100" height="100"/>
      <p>{{}}</p>
    </figure>
    <label class="description"> {{item.briefDesc}} </label>
    <label class="cost"> {{item.price}} </label>
  </div>
</template>

<script>
export default {
  name: "ItemThumbnail",

  props: {
    item: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped>
  .thumbnail{
    height: inherit;
    width: inherit;
    display: flex;
    justify-content: center;
    align-items: flex-start;

  }
  .picture{
    margin-top: 10px;
    border-radius: 10px;
    background-color: #475B5A;
    height: 55%;
    width: 98%;
  }
</style>
