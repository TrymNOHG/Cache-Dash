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
    background: #FFD700;
    border-radius: 50%;
    width: 100px;
    height: 100px;
    justify-content: center;
    align-content: center;
  }
</style>