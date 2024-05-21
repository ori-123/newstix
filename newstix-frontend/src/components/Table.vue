<script setup lang="ts">
  import { ref, onMounted } from "vue";
  import axios from "axios";

  const items = ref([]);

  onMounted(async function fetchNews() {
    try {
      const response = await axios.get('http://localhost:8080/api/news');
      items.value = response.data; // Extract the data from the response
    } catch (error) {
      console.error("Error fetching news: ", error);
    }
  });

</script>

<template>
  <div>
    <b-table striped hover :items="items"></b-table>
  </div>
</template>

<style scoped>

</style>