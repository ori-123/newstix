<script setup lang="ts">
import {
  BNavbar,
  BNavbarBrand,
  BNavbarToggle,
  BCollapse,
  BNavbarNav,
  BNavItem,
  BNavForm,
  BFormInput,
  BButton,
  BAvatar,
  BNavItemDropdown,
  BDropdownItem
} from 'bootstrap-vue-next';
import axios from "axios";
import router from "../router.ts";

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

function logout() {
  localStorage.clear();
  router.push('/login');
}

async function deleteAccount() {
  if (window.confirm("Are you sure you want to delete your account? This is irreversible!")) {
    await axios.delete('http://localhost:8080/api/users/delete', {
      data: { id: localStorage.getItem('userID') }
    });
    localStorage.clear();
    await router.push('/register');
  }
}

</script>

<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand>NewsTix</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">Personalize Feed</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-form>
            <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
            <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
          </b-nav-form>

          <b-avatar></b-avatar>

          <b-nav-item-dropdown right>
            <template #button-content>
              <em>User</em>
            </template>
            <b-dropdown-item @click="logout">Sign Out</b-dropdown-item>
            <b-dropdown-item @click="deleteAccount">Delete Account</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<style scoped>

</style>