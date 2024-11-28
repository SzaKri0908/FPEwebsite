<template>
<div class="pageWhole">
      <b-tabs content-class="mt-3" align="center">
        <b-tab title="Hírek" class="overflow-hidden" active>
          <AdminPageNews/>
        </b-tab>
        <b-tab title="Programok" class="overflow-hidden">
          <AdminPageEvents/>
        </b-tab>
      </b-tabs>
</div>
</template>
  
<script> 
import AdminPageEvents from './AdminPageEvents.vue'
import AdminPageNews from './AdminPageNews.vue';

  export default {
    name: 'AdminGalleryManager',
    components: {
      AdminPageNews, 
      AdminPageEvents
    },
    data() {
      return {
      };
    },
    methods: {
    }
  };
  </script>
  
  <style scoped>
  ::v-deep .nav-tabs .nav-link {
    height: 100%;
    width: 100%;
    color: #6c757d;
    font-size: 26px;  
  }

  ::v-deep .nav-tabs .nav-link.active,
  ::v-deep .nav-tabs .nav-link:hover {
    color: #23acf6 !important;
    box-shadow: inset 0 -4px 6px -4px rgba(0,0,0,0.4),
                inset -4px 0 6px -4px rgba(0,0,0,0.4), 
                inset 4px 0 6px -4px rgba(0,0,0,0.4);
  }

  ::v-deep .nav-tabs .nav-link:not(.active):hover {
    color: #212326;
  }

  ::v-deep .tab-content {
  font-size: 16px;
  }
  ::v-deep .tab-content {
  font-size: 16px;
  }
  </style>
  