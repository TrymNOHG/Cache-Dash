<template>
  <div class="grid">
    <new-item-component/>
  </div>
</template>

<script>
import newItemComponent from "@/components/pagesComponents/UserPage/NewItemComponent.vue";
export default {
  name: "newItemView",
  components: {newItemComponent}


}
</script>

<style scoped>
.grid{
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
}
</style>