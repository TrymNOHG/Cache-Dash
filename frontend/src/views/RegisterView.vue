<template>
  <div class="grid">
    <div></div>
    <register-component/>
  </div>
</template>

<script>
import RegisterComponent from "@/components/pagesComponents/AuthenticationPage/RegisterComponent.vue";

export default {
  name: "RegisterView",
  components: {RegisterComponent}
}
</script>

<style scoped>

.grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
}

</style>