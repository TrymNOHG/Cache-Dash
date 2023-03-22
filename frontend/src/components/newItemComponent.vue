<template>
  <form name="login-form" role="form" @submit="submit">
    <fieldset>
      <legend>{{$t('register')}}</legend>
      <label for="itemnameInput">{{$t('itemname')}}</label>
      <BasicInput
          id="itemnameInput"
          type="text"
          v-model="itemname"
          :error="errors.itemname"
          autocomplete="itemname"
      />
      <label for="priceInput">{{$t('price')}}</label>
      <BasicInput
          id="lastnameInput"
          type="double"
          v-model="price"
          :error="errors.price"
          autocomplete="price"
      />
      <label for="categoryInput">{{$t('category')}}</label>
      <BasicInput
          id="categoryInput"
          type="username"
          v-model="category"
          :error="errors.category"
          autocomplete="category"
      />
      <label for="briefInput">{{$t('briefdescription')}}</label>
      <BasicTextArea
          v-model="brief"
          :error="errors.brief"
      />
      <label for="fullInput">{{$t('fulldescription')}}</label>
      <BasicTextArea
          v-model="full"
          :error="errors.full"
      />
      <picture-upload-component v-model="imageData"/>
      <button
          id="submit_button"
          :disabled="hasErrors"
          type="submit"
          class="-fill-gradient"
      >
        {{$t('register')}}
      </button>
    </fieldset>
  </form>
</template>

<script>
import BasicInput from "@/components/basicInputComponents/BasicInput.vue";

import BasicTextArea from "@/components/basicInputComponents/BasicTextArea.vue";
import {ref} from "vue";
import {useStorage} from "vue3-storage";
import {useLoggedInStore} from "@/store/store";
import * as yup from "yup";
import {useField, useForm} from "vee-validate";
import {loginUser} from "@/services/Authenticator";
import router from "@/router/router";
import PictureUploadComponent from "@/components/basicInputComponents/pictureUploadComponent.vue";

export default {
  name: "newItemComponent",
  components: {PictureUploadComponent, BasicTextArea, BasicInput},

  data(){
    return{
      imageData: []
    }
  },

  setup () {
    const submitMessage = ref('');

    const validationSchema = yup.object({
      itemname: yup.string()
          .required('Item name is Required'),
      price: yup.string()
          .required('Price required'),
      brief: yup.string()
          .required('Brief description is required')
          .min(10),
      full: yup.string()
          .required('Full description is Required')
          .min(100),

    });

    const { handleSubmit, errors } = useForm({ validationSchema });
    const { value: itemname } = useField('itemname');
    const { value: price } = useField('price')
    const { value: brief } = useField('brief')
    const { value: full } = useField('full')
    const { value: category } = useField('category')



    const submit = handleSubmit(async () => {

    });

    return {
      itemname,
      price,
      brief,
      full,
      errors,
      category,
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
        itemname: this.itemname,
        price: this.price,
        brief: this.brief,
        full: this.full,
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

fieldset {
  margin: 25px;
  display: flex;
  flex-direction: column;
}


</style>