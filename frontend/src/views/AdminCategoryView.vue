<template>
  <div class="admin-catagory-window">
    <div class="main-categories">
      <AdminCategoriesComponents/>
    </div>
<!--    <PopUp v-if="trigger.pressedTrigger">-->
<!--      <h2>Attention</h2>-->
<!--    </PopUp>-->
    <div class="sub-categories">
      <AdminSubCategories/>
    </div>
    <div  class="simple-item-listing">

    </div>
  </div>
</template>

<script>
import AdminCategoriesComponents from "@/components/pagesComponents/AdminPages/AdminCategoriesComponents.vue";
// import PopUp from "@/components/basicInputComponents/PopUp.vue";
import { ref } from 'vue';
import AdminSubCategories from "@/components/pagesComponents/AdminPages/AdminSubCategories.vue";
export default {
  name: "AdminCategory",
  components:{AdminSubCategories, AdminCategoriesComponents},
  setup() {
    const trigger = ref({
      pressedTrigger: false,
      timerTrigger: false
    });

    return {
      trigger,

    }
  }

}
</script>

<style scoped>
  body{
    margin: 0px;
    height: 100%;
  }
  .admin-catagory-window{
    height: 100%;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-gap: 10px;

  }
  .main-categories{
    background-color: #7EB09B;
  }

  .sub-categories{

  }

</style>
