<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <img alt="Vue logo" class="logo" src="@/assets/Logo.svg" width="140" height="205" />

    <div class="wrapper">
      <ul>
        <RouterLink to="/simpleForm">simpleForm</RouterLink>
        <RouterLink to="/notFound">NotFound</RouterLink>
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

header {
  display: flex;
  text-align: center;
  background-color: white;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  border-left: 1px solid var(--color-border);
  padding: 10px;
}

nav a:first-of-type {
  border: 0;
}

a {
  padding: 10px;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
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