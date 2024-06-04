<script setup lang="ts">
import { ref, onMounted, watch } from "vue";
import axios from "axios";
import { BTable } from 'bootstrap-vue-next';

const props = defineProps({
  user: {
    type: Object,
    required: true,
    default: () => ({
      language: 'en',
      country: 'us',
      category: 'politics'
    })
  }
});

const items = ref([]);

const fetchNews = async () => {
  if (props.user) {
    try {
      const { language, country, category } = props.user;
      const response = await axios.get(`http://localhost:8080/api/news`, {
        params: {
          language,
          country,
          category
        }
      });
      items.value = response.data;
    } catch (error) {
      console.error("Error fetching news: ", error);
    }
  }
};

onMounted(fetchNews);

</script>

<template>
  <div>
    <b-table striped hover :items="items"></b-table>
  </div>
</template>

<style scoped>
/* Your styles here */
</style>
