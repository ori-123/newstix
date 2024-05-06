<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
import User from "../../types/User";

interface FormData {
  username: string,
  password: string
}

const formData = ref<FormData>({
  username: '',
  password: ''
});
const signInError = ref<string>('');

async function login() {
  try {
    await axios.post<User>('/api/users/register', formData.value);
    // TODO: redirect to Dashboard
  } catch (error: any) {
      signInError.value = 'An error occurred. Please try again later.';
  }
}

</script>

<template>
  <form @submit.prevent="login">
    Username:<br />
    <input v-model="formData.username" type="text" name="username" /><br />
    Password:<br />
    <input v-model="formData.password" type="password" name="password" /><br />
    <button type="submit">Register</button><br />
    <span v-if="signInError" style="color: red;">{{ signInError }}</span>
  </form>
</template>

<style>

</style>
