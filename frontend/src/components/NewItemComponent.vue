<template>
  <form name="login-form" role="form" @submit="submit">
    <fieldset>
      <legend>{{$t('newItem')}}</legend>
      <label for="briefInput">{{$t('briefdescription')}}</label>
      <BasicInput
          id="briefInput"
          type="text"
          v-model="brief"
          :error="errors.brief"
          autocomplete="name"
      />
      <label for="priceInput">{{$t('price')}}</label>
      <BasicInput
          id="lastnameInput"
          type="number"
          v-model="price"
          :error="errors.price"
          autocomplete="price"
      />
      <label for="categoryInput">{{$t('category')}}</label>
      <BasicSelect
          class="CategorySelect"
          :options="catStore.allCategoryNames"
          v-model="catStore.category.categoryName"
      />
      <label for="categoryInput">{{$t('county')}}</label>
      <BasicSelect
          class="CategorySelect"
          :options="countyStore.allCounties"
          v-model="countyStore.county.countyName"
      />
      <label for="cityInput">{{$t('city')}}</label>
      <BasicInput
          id="cityInput"
          type="text"
          v-model="city"
          :error="errors.city"
          autocomplete="shipping address-level2"
      />
      <label for="addressInput">{{$t('address')}}</label>
      <BasicInput
          id="addressInput"
          type="text"
          v-model="address"
          :error="errors.address"
          autocomplete="street-address"
      />

      <label for="keyInfoListInput">{{$t('keyInfoList')}}</label>
      <BasicInput
          id="keyInfoListInput"
          type="text"
          v-model="keyInfoList"
          :error="errors.keyInfoList"
          autocomplete="information"
      />
      <label for="fullInput">{{$t('fulldescription')}}</label>
      <BasicTextArea
          v-model="full"
          :error="errors.full"
      />
      <picture-upload-component v-on:send-image-data="addToImageList($event)"/>
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
import {useCategoryStore, useLoggedInStore, useCountyStore, useImageStore} from "@/store/store";
import * as yup from "yup";
import {useField, useForm} from "vee-validate";
import PictureUploadComponent from "@/components/basicInputComponents/pictureUploadComponent.vue";
import BasicRadioGroup from "@/components/basicInputComponents/BasicRadioGroup.vue";
import BasicSelect from "@/components/basicInputComponents/BasicSelect.vue";

export default {
  name: "newItemComponent",
  components: {BasicSelect, BasicRadioGroup, PictureUploadComponent, BasicTextArea, BasicInput},

  data(){
    return{
      catStore: useCategoryStore(),
      countyStore: useCountyStore(),

    }
  },

  setup () {
    const submitMessage = ref('');
    const userStore = useLoggedInStore();
    const catStore = useCategoryStore();
    const countyStore = useCountyStore();
    const imageStore = useImageStore();
    countyStore.$reset();
    catStore.$reset();

    const validationSchema = yup.object({
      price: yup.string()
          .required('Price required'),
      brief: yup.string()
          .required('Brief description is required')
          .min(10),
      full: yup.string()
          .required('Full description is Required')
          .min(100),
      address: yup.string()
          .required('Address is Required'),
      keyInfoList: yup.string()
          .required('Key information Required'),
      city: yup.string()
          .required('City is Required')
    });

    const { handleSubmit, errors } = useForm({ validationSchema });
    const { value: price } = useField('price')
    const { value: brief } = useField('brief')
    const { value: full } = useField('full')
    const { value: category } = useField('category')
    const { value: address } = useField('address')
    const { value: keyInfoList } = useField('keyInfoList')
    const { value: city } = useField('city')



    const submit = handleSubmit(async () => {
      catStore.setCorrectCategory(catStore.category.categoryName);

      const formData = new FormData();
      formData.append('username', userStore.user.username);
      formData.append('briefDesc', brief.value)
      formData.append('fullDesc', full.value)
      formData.append('city', city.value)
      formData.append('address', address.value)
      formData.append('county', countyStore.county.countyName)
      formData.append('categoryId', catStore.category.categoryID)
      formData.append('price', price.value)
      formData.append('keyInfoList', keyInfoList.value.split(" "))
      formData.append('images', imageStore.imageToSend)

      console.log(formData)

      imageStore.$reset();
    });

    return {
      price,
      brief,
      full,
      city,
      address,
      errors,
      category,
      keyInfoList,
      submit,
      validationSchema,
      submitMessage,
      catStore,
      countyStore,
      userStore,
      imageStore
    }
  },
  computed: {
    hasErrors() {
      return !this.validationSchema.isValidSync({
        price: this.price,
        brief: this.brief,
        full: this.full,
        keyInfoList: this.keyInfoList,
        address: this.address,
        city: this.city
      });
    },
  },
  methods: {
    addToImageList(theNewImage){
      console.log("kill", theNewImage)
      this.imageStore.addImage(theNewImage)
      console.log("me", this.imageStore.test)
    },

    handleSubmit() {
      const url = `https://api.mapbox.com/geocoding/v5/mapbox.places/${encodeURIComponent(this.address)}.json?access_token=pk.eyJ1IjoidG9tYWJlciIsImEiOiJjbGZsYmw0Ym0wMDNqM3BvMXNlZ213bXlvIn0.XAO9MuoT6FoiYXnbznnJqg`;
      fetch(url)
          .then(response => response.json())
          .then(data => {
            this.latitude = data.features[0].center[1];
            this.longitude = data.features[0].center[0];
          })
          .catch(error => console.error(error));
    }
  },
}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

form {
  padding: 20px;
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
  width: 100%;
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
  display: block;
  padding: 10px;
}

/* Common styles for button elements */
button {
  border: 2px solid black;
  padding: 10px 20px;
  background-color: #FFD700;
  color: black;
  margin: 10px 5px;
  border-radius: 5px;
  cursor: pointer;
}

button:active {
  background-color: white;
  color: #1E293B;
}

button:hover {
  color: #1E293B;
  background-color: white;
  border-color: #1E293B;
}

button:disabled {
  background-color: #b3a100;
  cursor: not-allowed;
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