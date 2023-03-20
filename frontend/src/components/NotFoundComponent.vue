<template>
<h1>Oops!</h1>
  <h3>The {{resource}} you're  looking for is not here.</h3>
</template>

<script>

export default {
  name: "NotFound",
  props: {
    resource: {
      type: String,
      required: true,
      default: 'page'
    }
  }
}
</script>


<style scoped>

</style>