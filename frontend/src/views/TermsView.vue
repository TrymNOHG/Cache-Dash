<template>
  <div class="grid">
    <div class="section1">
      <label class="section1">{{$t('section1')}}</label>
      <hr>
    </div>

    <div class="colums">
      <div class="section2">
        <label>{{$t('section2')}}</label>
      </div>
      <div class="section3">
        <label>{{$t('section3')}}</label>

        <button @click="$router.push('/register')">
          Back
        </button>
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
  grid-template-rows: 1fr 5fr;
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

.section3{
  display: flex;
  flex-direction: column;
}

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
</style>