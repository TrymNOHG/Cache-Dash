<template>
  <div class="grid">
    <div></div>
    <login-component/>
  </div>
</template>

<script>
import LoginComponent from "@/components/pagesComponents/AuthenticationPage/LoginComponent.vue";

export default {
  name: "LoginView",
  components: {LoginComponent}
}
</script>

<style scoped>
.grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
}
</style>