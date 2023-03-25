<template>
  <div class="grid">
    <form name="login-form" role="form" @submit="submit">
      <fieldset>
        <legend>{{$t('register')}}</legend>
        <div class="form-group">
          <label for="nameInput">{{$t('name')}}</label>
          <BasicInput
              id="nameInput"
              type="name"
              v-model="fullname"
              :error="errors.fullname"
              autocomplete="name"
          />
          <label for="usernameInput">{{$t('username')}}</label>
          <BasicInput
              id="usernameInput"
              type="username"
              v-model="username"
              :error="errors.username"
              autocomplete="username"
          />
          <label for="passwordInput">{{$t('password')}}</label>
          <BasicInput
              id="passwordInput"
              type="password"
              v-model="password"
              :error="errors.password"
              aria-autocomplete="list"
          />
          <label for="emailInput">Email</label>
          <BasicInput
              id="emailInput"
              type="email"
              v-model="email"
              :error="errors.email"
              autocomplete="email"
          />
          <label for="dateInput">{{$t('dateOfBirth')}}</label>
          <Dateinput
              id="dateInput"
              v-model="dateOfBirth"
              :error="errors.dateOfBirth"
          />
          <label for="phoneNumber">{{$t('phoneNumber')}}</label>
          <PhoneInput
              id="phoneNumber"
              v-model="phonenumber"
              :error="errors.phoneNumber"
          />
        <div>
            <BasicCheckbox
                v-model="termOfService"
                :error="errors.termOfService"
            >
            </BasicCheckbox>

            <label id="termsInput" for="termsInput" @click="$router.push('/register/terms')">{{ $t('termsOfService') }}</label>
          </div>
        </div>
        <div class="button-group">
          <button
              id="submit_button"
              :disabled="hasErrors"
              type="submit"
              class="-fill-gradient"
          >
            {{ $t('register') }}
          </button>
          <button class="login_button" @click="$router.push('/login')">{{$t('login')}}</button>
        </div>
      </fieldset>
    </form>
    <h2 v-if="submitMessage" id="submit-message" aria-describedby="login-form">{{ submitMessage }}</h2>
  </div>
</template>


<script>
import BasicInput from "@/components/basicInputComponents/BasicInput.vue";
import * as yup from 'yup'
import {useField, useForm } from "vee-validate";
import { useLoggedInStore } from "@/store/store";
import {ref} from "vue";
import router from "@/router/router";
import {registerUser} from "@/services/UserService";
import PhoneInput from "@/components/basicInputComponents/PhoneInput.vue";
import Dateinput from "@/components/basicInputComponents/Dateinput.vue";
import BasicCheckbox from "@/components/basicInputComponents/BasicCheckbox.vue";

export default {
  name: "RegisterComponent",
  components: {
    BasicCheckbox,
    BasicInput,
    Dateinput,
    PhoneInput
  },

  setup () {
    const store = useLoggedInStore();
    const submitMessage = ref('');

    const validationSchema = yup.object({
      fullname: yup.string()
          .required('Full Name is Required'),
      username: yup.string()
          .required('Username is Required'),
      password: yup.string()
          .required('Password required')
          .min(8),
      email: yup.string()
          .required('Email required'),
      // dateOfBirth: yup.date()
      //     .required('Date is required'),
      // phonenumber: yup.string()
      //     .required('Phone number is required'),
      termOfService: yup.bool()
          .required("Terms and Conditions must be checked")
    });

    const { handleSubmit, errors } = useForm({ validationSchema });
    const { value: fullname } = useField('fullname');
    const { value: username } = useField('username');
    const { value: password } = useField('password');
    const { value: email } = useField('email');
    const { value: dateOfBirth } = useField('dateOfBirth');
    const { value: phonenumber } = useField('phonenumber')
    const { value: termOfService } = useField('termOfService')

    const submit = handleSubmit(async () => {
      const userData = {
        username: username.value,
        password: password.value,
        fullName: fullname.value,
        email:  email.value,
        birthDate: dateOfBirth.value,
        phone: phonenumber.value,
        picture: null,
        role: "USER"
      }

      console.log(userData)

      await registerUser(userData).then(async response => {
        if (response !== undefined) {
          store.setSessionToken(response.data.token)
          await store.fetchUser()

          submitMessage.value = "Registration Successful";
          setTimeout(() => {
            submitMessage.value = "";
          }, 3000);
          await router.push("/");
        } else {
          submitMessage.value = "Something went wrong. Please try again later.";
          setTimeout(() => {
            submitMessage.value = "";
          }, 3000);
        }
      }).catch(error => {
        alert(error.body) //TODO: format!!!!
        console.warn('error1', error) //TODO: add exception handling
      })

    });

    return {
      fullname,
      password,
      username,
      email,
      dateOfBirth,
      phonenumber,
      termOfService,
      errors,
      submit,
      validationSchema,
      submitMessage,
      store
    }
  },

  computed: {
    hasErrors() {
      return !this.validationSchema.isValidSync({
        username: this.username,
        password: this.password,
        fullname: this.fullname,
        email: this.email,
        dateOfBirth: this.dateOfBirth,
        phonenumber: this.phonenumber,
        termOfService: this.termOfService,
      });
    },
  },
}
</script>

<style scoped>

* {
  box-sizing: border-box;
}

form {
  padding: 20px 0;
  grid-column: 2;
  background-color: #7EB09B;
}

#termsInput {
  text-decoration: underline black;
}

#termsInput:hover{
  color: white;
}

input,
textarea {
  padding: 10px;
  border-radius: 5px;
  border: 2px solid #ccc;
  margin-bottom: 10px;
  font-size: 1rem;
}

h1,
h2,
h3 {
  font-size: 2rem;
  margin: 0;
}

h1 {
  padding: 10px;
}

label{
  padding: 10px;
}

/* Common styles for button elements */
button {
  border-width: 2px;
  border-color: black;
  padding: 10px 20px;
  background-color: #FFD700;
  color: black;
  margin: 10px;
}

button:active {
  background-color: white;
  color: #1E293B;
}

button:hover {
  color: #1E293B !important;
  background-color: white !important;
}

button:disabled{
  color: black;
  opacity: 68%;
  background-color: #b3a100;
}

/* Style for error messages */
h5 {
  color: red;
  border: 1px solid red;
  padding: 5px;
  border-radius: 5px;
  margin: 10px 0;
}

.form-group {
  margin: 25px;
  display: flex;
  flex-direction: column;
}

.button-group {
  margin: 25px;
  display: flex;
  justify-content: center;
  flex-direction: column;
}

.login_button{
  background-color: white;
  color: black;
}

</style>
