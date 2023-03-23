<template>
  <div class="userSite-window">
    <my-items/>
    <personal-information/>
  </div>
</template>

<script>
import PersonalInformation from "@/components/pagesComponents/UserPage/personalInformationComponent.vue";
import MyItems from "@/components/pagesComponents/UserPage/myItemsComponent.vue";

export default {
  name: "myProfile",
  components: {MyItems, PersonalInformation}
}
</script>

<style scoped>
  .userSite-window{
    display: grid;
    grid-template-columns: 1fr 1fr;
  }
</style>