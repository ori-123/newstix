<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
import router from "../router.ts";

interface FormData {
  username: string,
  password: string
}

const formData = ref<FormData>({
  username: '',
  password: ''
});
const loginError = ref<string>('');

async function login() {
  try {
    const response = await axios.post('/api/users/login', formData.value);
    const token = response.data;
    localStorage.setItem('token', token);
    router.push('/dashboard');
  } catch (error: any) {
    if (error.response && error.response.status === 401) {
      loginError.value = 'Invalid username or password.';
    } else {
      loginError.value = 'An error occurred. Please try again later.';
    }
  }
}

</script>

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

<style>

</style>
