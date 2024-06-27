<script setup lang="ts">
import { ref, onMounted, watch } from "vue";
import axios from "axios";
import { BTable } from 'bootstrap-vue-next';

const props = defineProps({
  user: {
    type: Object,
    required: true,
    default: () => ({
      language: 'EN',
      country: 'US',
      category: 'POLITICS'
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
      items.value = response.data.map((newsItem) => {
        return {
          icon: `<img src="${newsItem.source_icon}" alt="icon" class="image-icon" width="200">`,
          image: `<img src="${newsItem.image_url}" alt="image" class="news-image" width="500">`,
          title: `<a href="${newsItem.link}">${newsItem.title}</a>`,
          summary: newsItem.description,
          published_at: newsItem.pubDate
        };
      });
    } catch (error) {
      console.error("Error fetching news: ", error);
    }
  }
};

onMounted(fetchNews);

watch(
    () => props.user,
    (newUser, oldUser) => {
      if (newUser.language !== oldUser.language || newUser.country !== oldUser.country || newUser.category !== oldUser.category) {
        fetchNews();
      }
    },
    { deep: true }
);

</script>

<template>
  <div>
    <b-table striped hover :items="items">
      <template #cell(icon)="data">
        <div v-html="data.item.icon"></div>
      </template>
      <template #cell(image)="data">
        <div v-html="data.item.image"></div>
      </template>
      <template #cell(title)="data">
        <div v-html="data.item.title"></div>
      </template>
      <template #cell(summary)="data">
        <div>{{ data.item.summary }}</div>
      </template>
      <template #cell(published_at)="data">
        <div>{{ data.item.published_at }}</div>
      </template>
    </b-table>
  </div>
</template>

<style scoped>
.image-icon {
  width: 10px;
  height: auto;
}

.news-image {
  width: 50px;
  height: auto;
}
</style>
