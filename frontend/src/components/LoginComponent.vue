<template>
  <div class="grid">
    <form name="login-form" role="form" @submit="submit">
      <fieldset>
        <legend>{{ $t('login') }}</legend>
        <div class="form-group">
          <label for="nameInput">{{ $t('username') }}</label>
          <BasicInput
              id="nameInput"
              type="text"
              v-model="username"
              :error="errors.username"
              autocomplete="username"
          />
          <label for="passwordInput">{{ $t('password') }}</label>
          <BasicInput
              id="passwordInput"
              type="password"
              v-model="password"
              :error="errors.password"
          />
        </div>
        <div class="button-group">
          <button
              id="submit_button"
              :disabled="hasErrors"
              type="submit"
              class="-fill-gradient"
          >
            {{ $t('login') }}
          </button>
          <button @click="$router.push('/register')">{{ $t('register') }}</button>
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


export default {
  name: "LoginComponent",
  components: {BasicInput},

  setup () {
    const submitMessage = ref('');
    const storage = useStorage();
    const store = useLoggedInStore();

    const validationSchema = yup.object({
      username: yup.string()
          .required('Username is Required'),
      password: yup.string()
          .required('Password required')
          .min(8)
    })

    const { handleSubmit, errors } = useForm({ validationSchema });
    const { value: username } = useField('username');
    const { value: password } = useField('password')


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
      password,
      username,
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
  grid-template-columns: 40% 20% 40%;
  margin-top: 10%;
  margin-bottom: 10%;
}

form {
  padding: 20px 0;
  grid-column: 2;
  background-color: #7EB09B;
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

fieldset{
  margin: 25px;

}

</style>