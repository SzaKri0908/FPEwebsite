<template>
  <div>
      <b-container fluid class="header">
        <router-link to="/home">
          <b-row>
              <b-col class="text-center">
                  <img class="logo" src="../assets/Rectangle 99.svg" alt="logo">
              </b-col>
          </b-row>
        </router-link>
        <NavbarView/>
      </b-container>
  </div>
</template>

<script>
  import NavbarView from './NavbarView.vue'
  export default {
    name: "HeaderView",
    components: {
      NavbarView,
    },
  };
</script>

<style scoped>
.header {
  padding: 24px 15px;
}

.logo {
  width: 181px; 
  height: 79px; 
  flex-shrink: 0;
}

@media (max-width: 768px) {
  .header {
    padding: 12px 10px; 
  }
  .logo {
    width: 120px;
    height: auto; 
  }
}
</style>
