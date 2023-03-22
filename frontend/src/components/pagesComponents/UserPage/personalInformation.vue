<template>
  <div class="container">

    <div class="label">
      <h2>Personal information</h2>
    </div>

    <div class="profile-picture-window">

    </div>

    <div class="profile-information">
      <label>{{$t('username')}}</label>
        <textarea v-if="edit">{{username}}</textarea>
        <label v-else>{{store.username}}</label>
      <label>{{$t('fullName')}}:</label>
        <textarea v-if="edit">{{ fullName }}</textarea>
        <label v-else>{{ store.fullName }}</label>
      <label>{{$t('email')}}:</label>
        <textarea v-if="edit">{{ email }}</textarea>
        <label v-else>{{ store.email }}</label>
      <label>{{$t('dateOfBirth')}}:</label>
        <textarea v-if="edit">{{ birth }}</textarea>
        <label v-else>{{ store.dateOfBirth }}</label>
      <label>{{$t('phoneNumber')}}:</label>
        <textarea v-if="edit">{{ phoneNum }}</textarea>
        <label v-else>{{ store.phoneNumber }}</label>
      <label>{{$t('address')}}:</label>
        <textarea v-if="edit">{{ address }}</textarea>
        <label v-else>{{ store.address }}</label>
      <label>{{$t('zipCode')}}:</label>
        <textarea v-if="edit">{{ zipCode }}</textarea>
        <label v-else>{{ store.zipCode }}</label>
      <div class="contact-Checkbox">
        <basic-checkbox
            label=' Show contact information and address in your listings'></basic-checkbox>
      </div>

    </div>
    <div class="edit-button">
      <button id="edit-info" @click="editUser()" >{{ button_name }}</button>
    </div>
  </div>
</template>

<script>
import BasicButton from "@/components/basicInputComponents/BasicButton.vue";
import BasicCheckbox from "@/components/basicInputComponents/BasicCheckbox.vue";
import {useLoggedInStore} from "@/store/store";
export default {
  name: "personalInformation",
  components: {BasicCheckbox},
  props: ['user'],

  data(){
    return{
      edit: false,
      store: useLoggedInStore(),
      button_name: "Edit user information",
      userData:{

      }
    }

  },
  methods: {
    editUser(){
      if(this.edit === true){
        this.edit = false
        this.button_name = 'Edit user information'
      } else{
        this.edit = true
        this.button_name = 'Save changes'
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

  /* Common styles for button elements */
  button {
    border-width: 2px;
    border-color: black;
    padding: 10px 20px;
    background-color: #FFD700;
    color: black;
    margin: 10px;
    box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
    border-radius: 5px;
  }

  button:active {
    background-color: white;
    color: #1E293B;
  }

  button:hover {
    color: #1E293B !important;
    background-color: white !important;
  }
</style>