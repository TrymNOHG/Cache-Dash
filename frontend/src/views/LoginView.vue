<template>
  <main>
    <login-component/>
  </main>
</template>

<script>
import LoginComponent from "@/components/pagesComponents/AuthenticationPage/LoginComponent.vue";

export default {
  name: "LoginView",
  components: {LoginComponent}
}
</script>

<style scoped>
main {
  text-align: center;
}
</style>