<template>
    <div>
      <h1>Random User</h1>
        <div
           class="user"
           v-for="user in users"
           :key="user.first"
        >
        <div class="user-avatar">
          <img :src="user.picture.large" />
        </div>
        <div class="user-details">
          <h2 class="user-name">
            {{ user.name.first }}
            {{ user.name.last }}
          </h2>
          <ul>
            <li><strong>Birthday:</strong> {{ formatDate(user.dob.date) }}</li>
            <li><strong>Location:</strong> {{ user.location.city }}, {{ user.location.state }}</li>
          </ul>
        </div>
      </div>
    </div>
  </template>
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        users: [],
      };
    },
    methods: {
      getInitialUsers() {
        axios.get(`https://randomuser.me/api/?results=5`).then((response) => {
          this.users = response.data.results;
        });
      },
      formatDate(dateString) {
        let convertedDate = new Date(dateString);
        return convertedDate.toDateString();
     },
     getNextUser() {
      window.onscroll = () => {
        let bottomOfWindow = document.documentElement.scrollTop + window.innerHeight === document.documentElement.offsetHeight;
        if (bottomOfWindow) {
          axios.get(`https://randomuser.me/api/`).then(response => {
            this.users.push(response.data.results[0]);
          });
        }
      }
    }
    },
    beforeMount() {
      this.getInitialUsers();
    },
    mounted() {
        this.getNextUser();
    }
  };
  </script>
  <style>
  .user {
    display: flex;
    background: #ccc;
    border-radius: 1em;
    margin: 1em auto;
  }
  
  .user-avatar {
    padding: 1em;
  }
  
  .user-avatar img {
    display: block;
    width: 100%;
    min-width: 64px;
    height: auto;
    border-radius: 50%;
  }
  
  .user-details {
    padding: 1em;
  }
  
  .user-name {
    margin: 0;
    padding: 0;
    font-size: 2rem;
    font-weight: 900;
  }
  </style>