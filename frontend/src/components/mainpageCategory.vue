<template>
  <div class="catergory-window">
    <div class="label">
      <label>Category</label>
    </div>
    <div class="category-list">
      <ul>
        <li v-for="category in categories">{{ category.name }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "mainpageCategory"
}
</script>

<style scoped>

  .catergory-window{
    display: grid;
    grid-template-rows: 1fr 4fr;
  }

  .category-list{
    background-color: #7EB09B;
    opacity: 70%;
  }
  .label{
    background-color: white;
    text-align: left;
    font-family: sans-serif;
    font-size: xx-large;
  }
</style>