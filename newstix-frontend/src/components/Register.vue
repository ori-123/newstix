<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
import User from "../../types/User";
import router from "../router";

interface FormData {
  username: string,
  password: string
}

const formData = ref<FormData>({
  username: '',
  password: ''
});
const signUpError = ref<string>('');

async function register() {
  try {
    await axios.post<User>('http://localhost:8080/api/users/register', formData.value);
    await router.push('/dashboard');
  } catch (error: any) {
      signUpError.value = 'An error occurred. Please try again later.';
  }
}

</script>

<template>
  <form @submit.prevent="register">
    Username:<br />
    <input v-model="formData.username" type="text" name="username" /><br />
    Password:<br />
    <input v-model="formData.password" type="password" name="password" /><br />
    <button type="submit">Register</button><br />
    <span v-if="signUpError" style="color: red;">{{ signUpError }}</span>
  </form>
</template>

<style>

</style>
