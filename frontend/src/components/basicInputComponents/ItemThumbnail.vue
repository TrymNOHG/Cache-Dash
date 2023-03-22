<template>
  <div class="thumbnail">
    <figure class="picture"> {{picture}} </figure>
    <label class="description"> {{description}} </label>
    <label class="cost"> {{cost}} </label>
  </div>
</template>

<script>
export default {
  name: "ItemThumbnail",

  props: {
    picture: null,
    description:'',
    cost:''
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