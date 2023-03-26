<template>
  <div class="admin-user-window">
    <div class="user-side">
      <personal-information/>
    </div>
    <div class="items">

    </div>
  </div>
</template>

<script>
import PersonalInformation from "@/components/pagesComponents/UserPage/PersonalInformationComponent.vue";

export default {
  name: "AdminUserView",
  components: {PersonalInformation}
}
</script>

<style scoped>
  .admin-user-window{
    display: grid;
    grid-template-columns: 1fr 1fr;
  }
  .user-side{
    grid-column: 1;
    border: black solid 2px;
    background-color: red;
  }
  .items{
    grid-column: 2;
    background-color: #2c3e50;
  }
</style>