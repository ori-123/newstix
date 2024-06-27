<script setup lang="ts">
import { onMounted, ref } from 'vue';
import axios from "axios";
import router from "../router";

const user = ref(null);
const language = ref('english');
const country = ref('united states of america');
const category = ref('politics');

onMounted(async function fetchUser() {
  try {
    const id = localStorage.getItem('userID');
    if (id) {
      const response = await axios.get(`http://localhost:8080/api/users/${id}`);
      user.value = response.data;
      if (user.value) {
        language.value = user.value.language;
        country.value = user.value.country;
        category.value = user.value.category;
      }
      console.log(user.value);
    } else {
      console.log('User ID not found in localStorage');
    }
  } catch (error) {
    console.error('Error fetching user data:', error);
  }
});


async function submitPreferences() {
  const userId = localStorage.getItem('userID');
  if (!userId) {
    console.error("User ID not found in local storage");
    return;
  }

  const countryMap = {
    "united states of america": "US",
    "germany": "DE",
    "belgium": "BE",
    "switzerland": "CH",
    "italy": "IT",
    "france": "FR",
    "hungary": "HU",
    "austria": "AT",
    "australia": "AU",
    "great britain": "GB",
    "ireland": "IE",
    "india": "IN",
    "canada": "CA"
  };

  const languageMap = {
    "english": "EN",
    "german": "DE",
    "italian": "IT",
    "french": "FR",
    "hungarian": "HU"
  };

  try {
    await axios.put(`http://localhost:8080/api/users/${userId}/preferences`, {
      language: languageMap[language.value],
      country: countryMap[country.value],
      category: category.value.toUpperCase()
    });
    alert('Preferences saved successfully');
    await router.push('/dashboard');
  } catch (error) {
    console.error("Failed to save preferences:", error);
  }
}

</script>

<template>
  <div class="preferences-container">
    <h2>Personalize Your Feed</h2>
    <form @submit.prevent="submitPreferences">
      <div class="form-group">
        <label for="language">Language</label>
        <select v-model="language" class="form-control" id="language">
          <option value="english">English</option>
          <option value="german">German</option>
          <option value="italian">Italian</option>
          <option value="french">French</option>
          <option value="hungarian">Hungarian</option>
        </select>
      </div>

      <div class="form-group">
        <label for="country">Country</label>
        <select v-model="country" class="form-control" id="country">
          <option value="united states of america">United States of America</option>
          <option value="germany">Germany</option>
          <option value="belgium">Belgium</option>
          <option value="switzerland">Switzerland</option>
          <option value="italy">Italy</option>
          <option value="france">France</option>
          <option value="hungary">Hungary</option>
          <option value="austria">Austria</option>
          <option value="australia">Australia</option>
          <option value="great britain">Great Britain</option>
          <option value="ireland">Ireland</option>
          <option value="india">India</option>
          <option value="canada">Canada</option>
        </select>
      </div>

      <div class="form-group">
        <label for="category">Category</label>
        <select v-model="category" class="form-control" id="category">
          <option value="business">Business</option>
          <option value="crime">Crime</option>
          <option value="politics">Politics</option>
          <option value="sports">Sports</option>
          <option value="entertainment">Entertainment</option>
          <option value="technology">Technology</option>
          <option value="lifestyle">Lifestyle</option>
        </select>
      </div>

      <button type="submit" class="btn btn-primary">Save Preferences</button>
    </form>
  </div>
</template>

<style scoped>

</style>