<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import Table from "./Table.vue";
import Navbar from "./Navbar.vue";
import axios from "axios";
import User from "../../types/User.ts";
import { Category, Country, Language, UserRole } from "../../types/Enums.ts";

const user = ref({
  id: '1',
  username: 'john_doe',
  roles: [UserRole.USER],
  language: Language.EN,
  country: Country.US,
  category: Category.POLITICS,
  timeframe: ''
});

onMounted(async function fetchUser() {
  try {
    const id = localStorage.getItem('userID');
    if (id) {
      const response = await axios.get(`http://localhost:8080/api/users/${id}`);
      user.value = response.data;
      console.log(user.value);
    } else {
      console.log('User ID not found in localStorage');
    }
  } catch (error) {
    console.error('Error fetching user data:', error);
  }
});

watch(user, (newUser) => {
  console.log(newUser);
  updateFrontendUser(newUser);
});

const frontendUser = ref(
    new User(
        user.value.id,
        user.value.username,
        '',
        user.value.roles,
        user.value.language,
        user.value.country,
        user.value.category,
        user.value.timeframe
    )
);

function updateFrontendUser(newUser: typeof user.value) {
  frontendUser.value = new User(
      newUser.id,
      newUser.username,
      '',
      newUser.roles,
      newUser.language,
      newUser.country,
      newUser.category,
      newUser.timeframe
  );
}

console.log("Frontend user: ", frontendUser);

</script>

<template>
  <div v-if="frontendUser">
    <Navbar :user="frontendUser"/>
    <Table :user="frontendUser"/>
  </div>
  <div v-else>
    Loading...
  </div>
</template>

<style scoped>
/* Add your styles here */
</style>
