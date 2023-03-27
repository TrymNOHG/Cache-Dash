<template>
  <div class="admin-user-window">
    <div>
    <mainpage-search-bar
      class="search"
      v-model="searchField"
    />
    </div>
    <div class="user-side">
      <AdminEditUserComponent/>
    </div>
    <div class="items">
      <MyItems

          items[]
          userRole='ADMIN'
          user="admin"
      />
    </div>
  </div>
</template>

<script>
import AdminEditUserComponent from "@/components/pagesComponents/AdminPages/AdminEditUserComponent.vue";
import MainpageSearchBar from "@/components/basicInputComponents/MainpageSearchBar.vue";
import MyItems from "@/components/pagesComponents/UserPage/MyItemsComponent.vue";

export default {
  name: "AdminUserView",
  components: {MyItems, MainpageSearchBar, AdminEditUserComponent},

  data() {
    return {
      searchField: '',

    }
  }
}
</script>

<style scoped>
  .admin-user-window{
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-gap: 10px;
  }
  .user-side{
    grid-column: 1;
    border: black solid 2px;
    margin-left: 10px;
  }
  .items{
    grid-column: 2;
    background-color: #2c3e50;
    margin-right: 10px;
  }
  .search{
    margin-left: 10px;
  }

</style>