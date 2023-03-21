<template>
  <div class="container">

    <div class="label">
      <h2>Personal information</h2>
    </div>

    <div class="profile-picture-window">

    </div>

    <div class="profile-information">
      <label>{{$t('fullName')}}:</label>
        <textarea v-if="edit">{{ fullName }}</textarea>
        <label v-else>{{ user.name }}</label>
      <label>{{$t('email')}}:</label>
        <textarea v-if="edit">{{ email }}</textarea>
        <label v-else>{{ user.email }}</label>
      <label>{{$t('dateOfBirth')}}:</label>
        <textarea v-if="edit">{{ birth }}</textarea>
        <label v-else>{{ user.birth }}</label>
      <label>{{$t('phoneNumber')}}:</label>
        <textarea v-if="edit">{{ phoneNum }}</textarea>
        <label v-else>{{ user.phonenum }}</label>
      <label>{{$t('address')}}:</label>
        <textarea v-if="edit">{{ address }}</textarea>
        <label v-else>{{ user.address }}</label>
      <label>{{$t('zipCode')}}:</label>
        <textarea v-if="edit">{{ zipCode }}</textarea>
        <label v-else>{{ user.zipcode }}</label>
      <basic-checkbox
      label='Show contact information and address in your listings'></basic-checkbox>
    </div>

    <div class="edit-button">
      <button id="edit-info" @click="editUser()" >Edit user information</button>
    </div>
  </div>
</template>

<script>
import BasicCheckbox from "@/components/basicInputComponents/BasicCheckbox.vue";

export default {
  name: "personalInformation",
  components: {BasicCheckbox},

  data(){
    return{
      edit: false,
    }
  },
  methods: {
    editUser(){
      if(this.edit === true){

      }
    }
  }
}
</script>

<style scoped>

  textarea{

  }

  .container{
    display: grid;
  }
  .profile-information{
    display: flex;
    flex-direction: column;
    text-align: left;
    margin-left: 10px;
    margin-right: 10px;
  }
</style>