<template>
  <div class="container">
    <div class="label">
      <h2>Personal information</h2>
    </div>

    <div class="user-details">
      <div class="profile-information">
        <label>{{$t('username')}}</label>
        <BasicInput
            v-if="edit"
            id="usernameInput"
            type="text"
            v-model="user.username"
            autocomplete="username"
        />
        <label v-else>{{user.username}}</label>
        <label>{{$t('fullName')}}:</label>
        <BasicInput
            v-if="edit"
            id="nameInput"
            type="text"
            v-model="user.fullName"
            autocomplete="name"
        />
        <label v-else>{{ user.fullName }}</label>
        <label>{{$t('email')}}:</label>
        <BasicInput
            v-if="edit"
            id="emailInput"
            type="email"
            v-model="user.email"
            autocomplete="email"
        />
        <label v-else>{{ user.email }}</label>
        <label>{{$t('dateOfBirth')}}:</label>
        <Dateinput
            v-if="edit"
            id="dateInput"
            v-model="user.birthDate"
        />
        <label v-else>{{ user.birthDate }}</label>
        <label>{{$t('phoneNumber')}}:</label>
        <BasicInput
            v-if="edit"
            id="phoneInput"
            type="phone"
            v-model="user.phone"
            autocomplete="phone-number"
        />
        <label v-else>{{ user.phone }}</label>
      </div>

<!--      <div class="profile-picture-window">-->
<!--        <div class="base-image-input" v-if="user.picture" :style="{ 'background-image': `url(${user.picture})` }">-->
<!--          <img :src="user.picture ? user.picture : ''" alt="Profile picture" />-->
<!--          <span v-if="!user.picture" class="placeholder">Choose an Image</span>-->
<!--        </div>-->
<!--        <input v-if="edit" class="input-image" ref="fileInput" type="file" @input="whenSelected"/>-->
<!--      </div>-->

      <div class="edit-button">
        <button id="edit-info" @click="editUser()" >{{ button_name }}</button>
        <button id="edit-info" @click="editPassword()" >{{ password_button }}</button>
      </div>
    </div>

    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <h3>Change Password</h3>
        <form @submit.prevent="saveNewPassword()">
          <label>Old Password:</label>
          <basic-input type="text" v-model="updatedPassword.oldPassword"/><br>
          <label>New Password:</label>
          <basic-input type="text" v-model="updatedPassword.newPassword"/><br>
          <button type="submit">Update Item</button>
          <span class="close" @click="hideUpdateModal()">X</span>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import BasicCheckbox from "@/components/basicInputComponents/BasicCheckbox.vue";
import {updateUser, updateUserPassword} from "@/services/UserService"
import * as store from "@/services/UserService";
import {useItemStore, useLoggedInStore} from "@/store/store";
import BasicInput from "@/components/basicInputComponents/BasicInput.vue";
import Dateinput from "@/components/basicInputComponents/Dateinput.vue";
export default {
  name: "personalInformation",
  components: {Dateinput, BasicInput, BasicCheckbox},

  setup(){
    const store = useLoggedInStore()
    // const itemStore = useItemStore();
    store.fetchUser();
    const user = store.getUser.data
    // user.picture = itemStore.convertImageBackToUrl(user.picture);

    return {
      store,
      user
    }
  },

  data(){
    return{
      edit: false,
      button_name: "Edit user information",
      password_button: "ChangePassword",
      showModal: false,
      updatedPassword: {
        oldPassword: '',
        newPassword: '',
      }
    }
  },
  methods: {
    editUser(){
      if(this.edit === true){
        //TODO: add exception handling for already existing username

        const completeUserDTO = this.userToFormData(this.user)

        console.log(completeUserDTO);

        updateUser(completeUserDTO).then((response) => {
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

    editPassword() {
      this.password_button = 'Save New Password'
      this.showModal = true;
    },

    async whenSelected() {
      const input = this.$refs.fileInput
      const files = input.files
      if (files && files[0]) {
        const reader = new FileReader
        reader.onload = e => {
          this.user.picture = e.target.result
        }
        reader.readAsDataURL(files[0])
        this.$emit('input', files[0])
      }
    },

    hideUpdateModal() {
      this.showModal = false;
    },

    userToFormData(user){
      const userDTO = {
        'username' : this.store.getUser.data.username,
        'newUsername' : user.username,
        'fullName' : user.fullName,
        'email' : user.email,
        'birthDate' : user.birthDate,
        'phone' : user.phone,
        'role' : user.role
      }

      // console.log("name:" +  userDTO.username)
      // const completeUserDTO = new FormData();
      // completeUserDTO.append('userUpdateDTO', JSON.stringify(userDTO))
      // console.log('User picture: ' + user.picture)
      // completeUserDTO.set('profilePicture', user.picture)

      return userDTO;
    },

    saveNewPassword() {

      const userPasswordUpdateDTO = {
        oldPassword: this.updatedPassword.oldPassword,
        newPassword: this.updatedPassword.newPassword
      }

      updateUserPassword(userPasswordUpdateDTO).then(response => {
        alert("Password has successfully been changed")
      }).catch(error => {
        alert("Something went wrong. Try writing old password again or logging out and in.")
      })


      this.updatedPassword = {
        oldPassword: '',
        newPassword: ''
      }
    }
  }
}
</script>

<style scoped>

img{
  height: 200px;
  width: 200px;
}

.container{
    background-color: #7EB09B;
  }

.base-image-input{
  width: 200px;
  height: 200px;
  margin: 5px;
  border: 1px solid #ccc;
  background-size: cover;
  background-position: center center;
  background-repeat: no-repeat;
}

.profile-picture-window{
  display: flex;
  float: right;
  background-color: lightgray;
  max-width: 200px;
  max-height: 200px;
}

.container{
    display: grid;
    grid-template-rows: 1fr 4fr 1fr;
  }

  .user-details{
    display: grid;
    grid-template-columns: 1fr 1fr;
  }

  .profile-information{
    display: flex;
    flex-direction: column;
    text-align: left;
    margin-left: 10px;
    margin-right: 10px;
  }

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
    color: white !important;
  }

.modal {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  top: 15%;
  width: 80%;
  height: 80%;

  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);

  padding: 20px;
  border-radius: 5px;
  z-index: 100;
  display: flex;
  flex-direction: column;
}

.modal-content {
  flex-grow: 1;
  overflow-y: auto;
}

.close {
  position: absolute;
  right: 10px;

  font-size: 15px;
  font-weight: bold;
  color: #ccc;
  cursor: pointer;
}

.close:hover{
  background-color: lightgray;
  color: black;
  width: 25px;
  height: 25px;
}

form {
  display: flex;
  flex-direction: column;
}

input, textarea {
  margin-bottom: 10px;
  padding: 5px;
}

.modal button {
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
  -4px -4px 8px rgba(255, 255, 255, 0.5),
  inset 1px 1px 2px rgba(0, 0, 0, 0.2),
  inset -1px -1px 2px rgba(255, 255, 255, 0.7);
  transform: translate(0, -2px);
  background-color: #FFD700;
  align-self: center;
  color: black;
  padding: 8px 16px;
  border: none;
  cursor: pointer;
  margin-top: 10px;
}

.modal button:hover {
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
  -4px -4px 8px rgba(255, 255, 255, 0.5),
  inset 1px 1px 2px rgba(0, 0, 0, 0.2),
  inset -1px -1px 2px rgba(255, 255, 255, 0.7);
  transform: translate(0, -2px);
  color: white !important;
  background-color: #4c9fdb;
}

</style>
