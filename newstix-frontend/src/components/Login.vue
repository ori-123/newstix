<template>
  <form @submit.prevent="login">
    Username:<br />
    <input v-model="formData.username" type="text" name="username" /><br />
    Password:<br />
    <input v-model="formData.password" type="password" name="password" /><br />
    <button type="submit">Sign in</button><br />
    <span v-if="loginError" style="color: red;">{{ loginError }}</span>
  </form>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
import {useRouter} from "vue-router";
const router = useRouter();

    const formData = ref({ username: '', password: '' });
    const loginError = ref('');

    async function login() {
      try {
        const response = await axios.post('http://localhost:8080/api/users/login', formData.value);
        const token = response.data.token;
        const userId = response.data.id;
        localStorage.setItem('token', token);
        localStorage.setItem('userID', userId);
        await router.push('/dashboard');
      } catch (error: any) {
        if (error.response && error.response.status === 401) {
          loginError.value = 'Invalid username or password.';
        } else {
          loginError.value = 'An error occurred. Please try again later.';
        }
      }
    }

</script>

<style>

</style>
