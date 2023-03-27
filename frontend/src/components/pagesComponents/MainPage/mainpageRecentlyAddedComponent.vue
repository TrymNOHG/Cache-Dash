<template>
  <div class="recentAdded-window">
    <div class="label">
      <h3>{{ $t('recentItems') }}</h3>
    </div>
    <div class="recent-list">
      <ul>
        <li v-for="item in items">{{ item }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "mainpageRecentylyAdded"
}
</script>

<style scoped>

  .recentAdded-window{
    display: grid;
    grid-template-rows: 1fr 4fr;
  }

  .recent-list{
    background-color: #7EB09B;
    opacity: 70%;
  }
  .label{
    background-color: white;
    text-align: left;
    font-family: sans-serif;
    font-size: xx-large;
  }

</style>