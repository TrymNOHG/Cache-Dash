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
              v-model="name"
              :error="errors.name"
              autocomplete="username"
          />
          <label for="nameInput">{{$t('username')}}</label>
          <BasicInput
              id="nameInput"
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
          />
          <label for="emailInput">Email</label>
          <BasicInput
              id="emailInput"
              type="email"
              v-model="email"
              :error="errors.email"
              autocomplete="username"
          />
          <label for="dateInput">{{$t('dateOfBirth')}}</label>
          <Dateinput
              id="dateInput"
              v-model="dateOfBirth"
              :error="errors.dateOfBirth"
              required
          />
          <label for="phoneNumber">{{$t('phoneNumber')}}</label>
          <PhoneInput
              id="phoneNumber"
              v-model="phonenumber"
              :error="errors.phoneNumber"
              required
              name="phoneNumber"
          />
          <div>
            <BasicCheckbox
                v-model="termOfService"
            />
            <label id="termsInput" for="termsInput" @click="$router.push('/terms')">{{ $t('termsOfService') }}</label>
          </div>
        </div>
        <div class="button-group">
          <button
              id="submit_button"
              :disabled="hasErrors"
              type="submit"
              class="-fill-gradient"
          >
            {{$t('register')}}
          </button>
          <button @click="$router.push('/login')">{{$t('login')}}</button>
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
import {useLoggedInStore} from "@/store/store";
import {ref} from "vue";
import router from "@/router/router";
import {loginUser} from "@/services/Authenticator";
import { useStorage } from 'vue3-storage';
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
    const submitMessage = ref('');
    const storage = useStorage();
    const store = useLoggedInStore();

    const validationSchema = yup.object({
      username: yup.string()
          .required('Username is Required'),
      email: yup.string()
          .required('Email required'),
      password: yup.string()
          .required('Password required')
          .min(8),
      firstname: yup.string()
          .required('First name is Required'),
      lastname: yup.string()
          .required('Last name is required'),
      dateOfBirth: yup.date()
          .required('Date of Birth required'),
      phonenumber: yup.string()
          .required('Phone Number required'),
    })

    const { handleSubmit, errors } = useForm({ validationSchema });
    const { value: username } = useField('username');
    const { value: password } = useField('password')
    const { value: email } = useField('email')
    const { value: firstname } = useField('firstname')
    const { value: lastname } = useField('lastname')
    const { value: dateOfBirth } = useField('dateOfBirth')
    const { value: phonenumber } = useField('phonenumber')
    const { value: termOfService } = useField('termOfService')


    const submit = handleSubmit(async () => {
      const token = await loginUser(username.value, password.value)
      if (token !== undefined) {
        console.log(token)
        storage.setStorageSync('token', token.token);
        storage.setStorageSync('username', username.value);
        await store.setToken(token);
        await store.setUsername(username.value);
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
    });

    return {
      firstname,
      dateOfBirth,
      lastname,
      password,
      username,
      termOfService,
      email,
      phonenumber,
      errors,
      submit,
      validationSchema,
      submitMessage,
    }
  },
  computed: {
    hasErrors() {
      return !this.validationSchema.isValidSync({
        username: this.username,
        password: this.password,
        firstname: this.firstname,
        lastname: this.lastname,
        dateOfBirth: this.dateOfBirth,
        termOfService: this.termOfService,
        email: this.email,
        phonenumber: this.phonenumber,

      });
    },
  },
}
</script>

<style scoped>

* {
  box-sizing: border-box;
}

.grid {
  display: grid;
  grid-template-columns: 2fr 1fr 2fr;
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

/* Style for error messages */
h5 {
  color: red;
  border: 1px solid red;
  padding: 5px;
  border-radius: 5px;
  margin: 10px 0;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.button-group {
  display: flex;
  justify-content: center;
}

</style>