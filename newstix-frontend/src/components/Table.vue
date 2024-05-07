<script setup lang="ts">
  import { ref, onMounted } from "vue";

  const items = ref([]);

  // TODO: get user in context somehow and fetch newsitems from the API

</script>

<template>
  <div>
    <b-table striped hover :items="items"></b-table>
  </div>
</template>

<style scoped>

</style>