<template>
  <div class="grid">
    <div class="section1">
      <label class="section1">{{$t('section1')}}</label>
    </div>
    <hr>
    <div class="colums">
      <div class="section2">
        <label>{{$t('section2')}}</label>
      </div>
      <div class="section3">
        <label>{{$t('section3')}}</label>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TermsView"
}
</script>

<style scoped>
.grid{
  display: grid;
  grid-template-rows: 1fr 0fr 5fr;
  background-color: #7EB09B;
}

.colums{
  display: grid;
  grid-template-columns: 1fr 1fr;
}

label{
  white-space: pre;
}
.section1{
  font-weight: bold;
}
</style>