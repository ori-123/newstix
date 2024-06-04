<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import Table from "./Table.vue";
import Navbar from "./Navbar.vue";
import axios from "axios";

const user = ref(null);

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
});
</script>

<template>
  <div v-if="user">
    <Navbar :user="user.value"/>
    <Table :user="user.value"/>
  </div>
  <div v-else>
    Loading...
  </div>
</template>

<style scoped>

</style>
