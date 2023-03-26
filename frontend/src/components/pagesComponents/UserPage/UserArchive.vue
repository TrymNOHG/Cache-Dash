<template>
  <ul class="container">
    <li v-for="item in items" :key="item.id">{{ item.name }}</li>
  </ul>
</template>

<script>
export default {
  name: "userArchive",

  props: {
    items: {
      type: Array,
      required: true,
    },
  },
}
</script>

<style scoped>
.container{
  background-color: red;
}
</style>