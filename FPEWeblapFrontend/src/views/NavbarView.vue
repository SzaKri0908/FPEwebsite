<template>
  <div>
    <b-row class="navBar">
      <b-col>
        <b-nav align="center" class="navItems" v-if="!isMobile">
          <b-nav-item active>
            <router-link to="/home">
              <b>Fiatalok Pécsért Egyesület</b>
            </router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link to="/news">
              <b>Hírek</b> 
            </router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link to="/programs">
              <b>Programok</b>
            </router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link to="/gallery">
              <b>Galéria</b>
            </router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link to="/contact">
              <b>Kapcsolat</b>
            </router-link>
          </b-nav-item>
        </b-nav>
      </b-col>
    </b-row>
    <b-button v-b-toggle.sidebarRight class="hamburger-menu" v-if="isMobile && !showSidebar" variant="*">☰</b-button>
    <b-sidebar id="sidebarRight" right shadow v-model="showSidebar" v-if="isMobile" backdrop >
      <b-nav vertical class="navItems">
        <b-nav-item active>
          <router-link to="/home"><b>Fiatalok Pécsért Egyesület</b></router-link>
        </b-nav-item>
        <b-nav-item>
          <router-link to="/news"><b>Hírek</b></router-link>
        </b-nav-item>
        <b-nav-item>
          <router-link to="/programs"><b>Programok</b></router-link>
        </b-nav-item>
        <b-nav-item>
          <router-link to="/gallery"><b>Galéria</b></router-link>
        </b-nav-item>
        <b-nav-item>
          <router-link to="/contact"><b>Kapcsolat</b></router-link>
        </b-nav-item>
      </b-nav>
    </b-sidebar>
  </div>
</template>

<script>
  import { deviceMixin } from '@/deviceMixin';
  export default {
    name: "NavbarView",
    mixins: [deviceMixin],
    data() {
      return {
        showSidebar: false,
      };
    },
  };
</script>

<style scoped>
  .btn:focus {
    outline: none !important;
    box-shadow: none !important;
  }
  .navItems a{
    text-decoration: none;
  }
  .hamburger-menu {
    position: fixed;
    right: 0;
    top: 0;
    z-index: 1050; 
  }
  .navBar {
    flex-shrink: 0;
    margin-top: 24px;
  }
  b {
    color: black;
  }
  b:hover {
    color: rgba(244, 188, 67, 1);
  }
  .navItems a {
    text-decoration: none;
  }
</style>
