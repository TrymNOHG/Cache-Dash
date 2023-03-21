<template>
  <div>
    <label v-if="label">{{ label }}</label>
    <input
        v-bind="$attrs"
        v-model="formattedPhoneNumber"
        type="tel"
        :placeholder="'Phone Number'"
        @input="$emit('update:modelValue', formattedPhoneNumber)"        class="field"
        :style="{ borderColor: error ? 'red' : ''}"
        aria-invalid='error ? true : null'
    >
    <p v-if="error" class="errorMessage" aria-live="assertive">{{ error }}</p>
  </div>
</template>

<script>
export default {
  props: {
    label: {
      type: String,
      default: ''
    },
    modelValue: {
      type: [String, Number],
      default: ''
    },
    error: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      formattedPhoneNumber: '',
      unformattedPhoneNumber: ''
    }
  },
  watch: {
    modelValue: {
      immediate: true,
      handler(newVal) {
        this.formattedPhoneNumber = this.formatPhoneNumber(newVal);
        this.unformattedPhoneNumber = newVal;
      }
    }
  },
  methods: {
    formatPhoneNumber(phoneNumber) {
      // Strip out non-digit characters
      phoneNumber = phoneNumber.replace(/\D/g, '');

      // If the phone number starts with '0047', replace it with '0'
      if (phoneNumber.startsWith('0047')) {
        phoneNumber = phoneNumber.replace(/^0047/, '0');
      }

      // If the phone number starts with '+47', replace it with '0'
      if (phoneNumber.startsWith('+47')) {
        phoneNumber = phoneNumber.replace(/^\+47/, '0');
      }

      // Format the phone number as XXX XX XXX
      return `${phoneNumber.slice(0, 3)} ${phoneNumber.slice(3, 5)} ${phoneNumber.slice(5, 8)}`;
    }
  },
  computed: {
    isValid() {
      // Validate that the phone number is exactly 8 digits
      return /^\d{8}$/.test(this.unformattedPhoneNumber);
    }
  }
}
</script>

<style scoped>
input {
  padding: 5px;
}

p {
  color: red;
  font-size: 10px;
}
</style>