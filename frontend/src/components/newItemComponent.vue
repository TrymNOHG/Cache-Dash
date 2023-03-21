<template>
  <form name="login-form" role="form" @submit="submit">
    <fieldset>
      <legend>{{$t('register')}}</legend>
      <div class="form-group">
        <label for="itemnameInput">{{$t('itemname')}}</label>
        <BasicInput
            id="itemnameInput"
            type="text"
            v-model="itemname"
            :error="errors.itemname"
            autocomplete="username"
        />
        <label for="priceInput">{{$t('price')}}</label>
        <BasicInput
            id="lastnameInput"
            type="double"
            v-model="price"
            :error="errors.price"
            autocomplete="username"
        />
        <label for="categoryInput">{{$t('category')}}</label>
        <BasicInput
            id="categoryInput"
            type="username"
            v-model="category"
            :error="errors.category"
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
</template>

<script>
import BasicInput from "@/components/basicInputComponents/BasicInput.vue";
import Dateinput from "@/components/basicInputComponents/Dateinput.vue";
import PhoneInput from "@/components/basicInputComponents/PhoneInput.vue";
import BasicCheckbox from "@/components/basicInputComponents/BasicCheckbox.vue";

export default {
  name: "newItemView",
  components: {BasicCheckbox, PhoneInput, Dateinput, BasicInput}
}
</script>

<style scoped>

</style>