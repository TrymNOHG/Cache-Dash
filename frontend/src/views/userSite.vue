<template>
  <div class="userSite-window">
    <my-items></my-items>
    <personal-information></personal-information>
  </div>
</template>

<script>
import PersonalInformation from "@/components/personalInformation.vue";
import MyItems from "@/components/myItems.vue";

export default {
  name: "userSite",
  components: {MyItems, PersonalInformation}
}
</script>

<style scoped>
  .userSite-window{
    display: grid;
    grid-template-columns: 1fr 1fr;
  }
</style>