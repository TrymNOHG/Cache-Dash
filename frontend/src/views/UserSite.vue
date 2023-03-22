<template>
  <div class="userSite-window">
    <my-items/>
    <personal-information/>
  </div>
</template>

<script>
import PersonalInformation from "@/components/pagesComponents/UserPage/personalInformation.vue";
import MyItems from "@/components/pagesComponents/UserPage/myItems.vue";

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