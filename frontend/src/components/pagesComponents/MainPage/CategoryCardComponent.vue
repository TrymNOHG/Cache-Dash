<template>
  <div class="figure">
    <p>{{ category }}</p>
  </div>
</template>

<script>
export default {
  name: "CategoryCard",
  props: ['category'],

  data() {
    return {
    }
  }
}
</script>

<style scoped>
  .figure{
    border-radius: 50%;
    border-color: black;
    width: inherit;
    height: inherit;
    display: flex;
    justify-content:center;
    align-items:center;

  }
</style>