<template>
  <div class="grid">
    <div></div>
    <new-item-component/>
    <div></div>
  </div>
</template>

<script>
import newItemComponent from "@/components/newItemComponent.vue";
import NotFound from "@/components/NotFoundComponent.vue";
export default {
  name: "newItemView",
  components: {newItemComponent}


}
</script>

<style scoped>
.grid{
  display: grid;
  grid-template-columns: 1fr 5fr 1fr;
}
</style>