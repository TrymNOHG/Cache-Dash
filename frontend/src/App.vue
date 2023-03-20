<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <img alt="Vue logo" class="logo" src="@/assets/Logo.jpg" width="100" height="100" />

    <div class="wrapper">
      <ul>
        <RouterLink to="/simpleForm">Chat</RouterLink>
        <RouterLink to="/notFound">New Item</RouterLink>
        <RouterLink to="/notFound">Auction</RouterLink>
      </ul>
    </div>
  </header>

  <main>
    <RouterView/>
  </main>
</template>

<style>
ul {
  list-style-type: none;
}

main{
  text-align: center;
}

header {
  display: flex;
  align-items: end;
  text-align: end;
  background-color: #475B5A;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}
a {
  width: 30px;
  padding: 10px;
  margin: 5px;
  background-color: #D9D9D9;
  color: black;
}


@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
    flex-direction: row-reverse;
    flex-wrap: nowrap;
    justify-content: space-between;
    align-items: center;
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>