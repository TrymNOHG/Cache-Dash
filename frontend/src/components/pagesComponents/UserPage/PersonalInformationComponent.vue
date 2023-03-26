<template>
  <div class="container">

    <div class="label">
      <h2>Personal information</h2>
    </div>

    <div class="profile-picture-window">

    </div>

    <div class="profile-information">
      <label>{{$t('username')}}</label>
        <basic-input v-if="edit">{{ user.username }}</basic-input>
        <label v-else>{{user.username}}</label>
      <label>{{$t('fullName')}}:</label>
        <textarea v-if="edit">{{ user.fullName }}</textarea>
        <label v-else>{{ user.fullName }}</label>
      <label>{{$t('email')}}:</label>
        <textarea v-if="edit">{{ user.email }}</textarea>
        <label v-else>{{ user.email }}</label>
      <label>{{$t('dateOfBirth')}}:</label>
        <textarea v-if="edit">{{ user.birthDate }}</textarea>
        <label v-else>{{ user.birthDate }}</label>
      <label>{{$t('phoneNumber')}}:</label>
        <textarea v-if="edit">{{ user.phone }}</textarea>
        <label v-else>{{ user.phone }}</label>
      <label>{{$t('address')}}:</label>
        <textarea v-if="edit">{{ address }}</textarea>
        <label v-else>{{  }}</label>
      <label>{{$t('zipCode')}}:</label>
        <textarea v-if="edit">{{ zipCode }}</textarea>
        <label v-else>{{  }}</label>


    </div>
    <div class="edit-button" v-if="role === 'ADMIN'">
      <button id="edit-info" @click="deleteUser()" >Delete user</button>
    </div>
    <div class="edit-button" v-else>
      <button id="edit-info" @click="editUser()" >{{ button_name }}</button>
    </div>
  </div>
</template>

<script setup>
import { useLoggedInStore } from "@/store/store";
import BasicInput from "@/components/basicInputComponents/BasicInput.vue";

const store = useLoggedInStore()
store.fetchUser()

const user = store.getUser.data

const role = user.role

</script>

<script>
import BasicCheckbox from "@/components/basicInputComponents/BasicCheckbox.vue";
import { updateUser } from "@/services/UserService"
export default {
  name: "personalInformation",
  components: {BasicCheckbox},

  data(){
    return{
      edit: false,
      button_name: "Edit user information",
      userUpdateDTO:{
        username : null,
        newUsername : null,
        fullName : null,
        email : null,
        birthDate : null,
        phone : null,
        picture : null,
      }
    }

  },
  methods: {
    editUser(){
      if(this.edit === true){
        //TODO: add exception handling for already existing username
        updateUser(this.userUpdateDTO).then((response) => {
          //TODO: is there a new token generated?
          console.log('Response', response);
        }).catch((error) => {
          console.warn(error);
        })
        this.edit = false
        this.button_name = 'Edit user information'
      } else{
        this.edit = true
        this.button_name = 'Save changes'
      }
    },
    deleteUser(){

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
  /*
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

 */

  button {
    border-width: 2px;
    border-color: black;
    padding: 10px 20px;
    background-color: #FFD700;
    color: black;
    margin: 10px;
    border-radius: 5px;
    box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5),
    -2px -2px 4px rgba(255, 255, 255, 0.5),
    inset 1px 1px 2px rgba(0, 0, 0, 0.2),
    inset -1px -1px 2px rgba(255, 255, 255, 0.7);
    transform: translate(0, -1px);
    transition: all 0.1s ease-in-out;
  }

  button:active {
    background-color: white;
    color: #1E293B;
  }

  button:hover {
    box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
    -4px -4px 8px rgba(255, 255, 255, 0.5),
    inset 1px 1px 2px rgba(0, 0, 0, 0.2),
    inset -1px -1px 2px rgba(255, 255, 255, 0.7);
    transform: translate(0, -2px);
    color: #1E293B !important;
  }


</style>
