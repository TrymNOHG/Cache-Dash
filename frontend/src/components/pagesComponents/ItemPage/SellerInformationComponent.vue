<template>
  <div class="seller-information-window">
    <div></div>
    <div class="button-div">
      <button class="chat-button" >Talk to seller</button>
    </div>
    <h2>Seller information</h2>
    <div class="profile-picture">
      <figure></figure>
    </div>
    <div v-if="user">
      <label>User name:</label>
      <p>{{ user.fullName }}</p>
      <label>Email:</label>
      <p> {{ user.email }} </p>
      <label>Phone number:</label>
      <p> {{ user.phone }} </p>
      <label>Address:</label>
      <p> {{ user.address }} </p>
    </div>
  </div>
</template>

<script>
import {ref} from "vue";
import { loadUserByUsername } from "@/services/UserService";

export default {
  name: "SellerInformation",
  props: {
    username: {
      type: String,
      required: true
    }
  },
  setup(props) {
    const user = ref(null)

    loadUserByUsername(props.username).then(response => {
      const {userId, username, fullName, email, birthDate, phone, picture } = response.data
      user.value = {userId, username, fullName, email, birthDate, phone, picture}
    }).catch(error => {
      console.log('error: ', error)
    })

    return {
      user
    }

  }
}
</script>

<style scoped>
  .seller-information-window{
    display: grid;
    grid-template-rows: 2fr 1fr 1fr 2fr 4fr;
  }
  .button-div{
    grid-row: 2;
  }

  .chat-button{
    height: inherit;
  }
</style>
