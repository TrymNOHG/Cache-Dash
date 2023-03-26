<template>
  <ul class="container">
    <h3>Archives</h3>
    <hr>
    <li v-for="item in items" :key="item.id" class="list-item">
      <span class="text">{{ item.briefDesc }}</span>
      <button class="sell-button">Sell</button>
    </li>
  </ul>
</template>

<script>
export default {
  name: "userArchive",

  props: {
    items: {
      type: Array,
      required: true,
    },
  },
}
</script>

<style scoped>
.container {
  background-color: white;
  display: flex;
  flex-direction: column;
  text-align: left;
}

.list-item {
  color: blue;
  background-color: white;
  border: black 2px solid;
  box-shadow: 0 3px 10px rgb(0 0 0 / 0.2);
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
  padding: 10px;
}

.list-item:hover {
  background-color: white;
}

.sell-button {
  border-width: 2px;
  border-color: black;
  padding: 10px 20px;
  background-color: #FFD700;
  color: black;
  border-radius: 5px;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5),
  -2px -2px 4px rgba(255, 255, 255, 0.5),
  inset 1px 1px 2px rgba(0, 0, 0, 0.2),
  inset -1px -1px 2px rgba(255, 255, 255, 0.7);
  transform: translate(0, -1px);
  transition: all 0.1s ease-in-out;
}

.sell-button:active {
  background-color: white;
  color: #1E293B;
}

.sell-button:hover {
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5),
  -4px -4px 8px rgba(255, 255, 255, 0.5),
  inset 1px 1px 2px rgba(0, 0, 0, 0.2),
  inset -1px -1px 2px rgba(255, 255, 255, 0.7);
  transform: translate(0, -2px);
  color: white !important;
}

.text {
  flex: 1;
}
</style>