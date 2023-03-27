<script setup>
import { RouterLink, RouterView } from 'vue-router'
import i18n from "@/locales/i18n";
</script>

<template>
  <header v-if="isAdmin && isLoggedIn">
    <img @click="$router.push('/')" alt="Vue logo" class="logo" src="@/assets/Logo.jpg" width="100" height="100" />
    <div class="wrapper">
      <ul>
        <RouterLink to="/admin/users">{{ $t ("users") }}</RouterLink>
        <RouterLink to="/admin">{{ $t ("admin") }}</RouterLink>
        <RouterLink to="/chat">{{ $t ("chat") }}</RouterLink>
        <RouterLink to="/newItem">{{ $t ("newItem") }}</RouterLink>
        <RouterLink to="/auction">{{ $t ("auctions") }}</RouterLink>
        <div class="language" @click="changeLanguage()">{{language}}</div>
        <RouterLink v-if="store.isLoggedIn" :to="'/my-profile'">
          <font-awesome-icon icon="fa-solid fa-circle-user" size="2xl"/>
        </RouterLink>
        <RouterLink v-else :to="'/login'">
          <font-awesome-icon icon="fa-solid fa-circle-user" size="2xl"/>
        </RouterLink>
      </ul>
    </div>
  </header>

  <header v-else>
    <img @click="$router.push('/')" alt="Vue logo" class="logo" src="@/assets/Logo.png" width="100" height="100" />
    <div class="wrapper">
      <ul>
        <RouterLink to="/chat">{{ $t ("chat") }}</RouterLink>
        <RouterLink to="/newItem">{{ $t ("newItem") }}</RouterLink>
        <RouterLink to="/auction">{{ $t ("auctions") }}</RouterLink>
        <div class="language" @click="changeLanguage()">{{language}}</div>
        <RouterLink v-if="store.isLoggedIn" :to="'/my-profile'">
          <font-awesome-icon icon="fa-solid fa-circle-user" size="2xl"/>
        </RouterLink>
        <RouterLink v-else :to="'/login'">
          <font-awesome-icon icon="fa-solid fa-circle-user" size="2xl"/>
        </RouterLink>
      </ul>
    </div>
  </header>
  <main>
    <RouterView/>
  </main>
  <footer>
  </footer>
</template>


<script>
import {useLoggedInStore} from "@/store/store";

export default {
  data(){
    return{
      store: useLoggedInStore(),
      language: "NO",
    }
  },
  methods: {
    changeLanguage() {
      if (this.language === "NO"){
        console.log(this.store.isLoggedIn)
        console.log(this.store.getUser.data.role)
        i18n.global.locale = "no"
        this.language = "EN";
      } else {
        i18n.global.locale = "en"
        this.language = "NO"

      }
    }
  },
  setup() {
    const store = useLoggedInStore();
    const isAdmin = computed(() => {
      return store.getUser.data.role === 'ADMIN'
    })

    const isLoggedIn = computed(() => {
      return store.isLoggedIn
    })

    return {
      isAdmin,
      isLoggedIn
    }
  }
}
</script>


<style>

header, footer{
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #475B5A;
  padding: 1rem;
}

.logo {
  display: block;
  margin-right: 2rem;
}

.logo:hover{
  background-color: rgba(18, 33, 34, 0.81);
  color: #818b96;
}

nav {
  display: flex;
  align-items: center;
}

ul {
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0;
}

li {
  margin-right: 1rem;
}

a {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.5rem 1rem;
  background-color: #D9D9D9;
  color: black;
  margin: 5px;
  min-width: 2rem;
  text-align: center;
  border-radius: 0.25rem;
}

.language {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.5rem;
  background-color: #D9D9D9;
  color: black;
  min-width: 2rem;
  text-align: center;
  border-radius: 0.25rem;
  margin-right: 1rem;
}

.language:hover {
  background-color: rgba(18, 33, 34, 0.81);
  color: #818b96;
}

.profile-link {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #000;
  color: #fff;
  width: 2.5rem;
  height: 2.5rem;
  border-radius: 50%;
  margin-left: 1rem;
}

main{
  text-align: center;
}


@media (max-width: 768px) {
  header {
    flex-direction: column;
    text-align: center;
  }

  .logo {
    margin: 0 0 1rem 0;
  }

  nav {
    margin-top: 1rem;
  }

  ul {
    flex-wrap: wrap;
    justify-content: center;
  }

  li {
    margin: 0.5rem;
  }
}
</style>
