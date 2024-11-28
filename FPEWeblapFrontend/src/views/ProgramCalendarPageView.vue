<template>
    <div>
        <b-container class="ProgramCalendarPageViewContainer">
            <b-row>
                <b-col :cols="isMobile ? '9' : '10'">
                    <p class="title">Programnaptár</p>
                </b-col>
                <b-col :cols="isMobile ? '3' : '2'">
                    <img class="tigerTransparent" src="../assets/TigerTransparent.svg" alt="tigerTransparent">
                </b-col>
            </b-row>
        </b-container>
        <ProgramCalendarView :isPage="true"/>
    </div>
</template>
  
  <script>
      import ProgramCalendarView from './ProgramCalendarView.vue'
      import { deviceMixin } from '@/deviceMixin';
  
      export default {
        name: 'ProgramCalendarPageView',
        components: {
            ProgramCalendarView,
        },
        mixins: [deviceMixin],
      }
  </script>
  
  <style scoped>
  .ProgramCalendarPageViewContainer {
    padding: 0px 70px;
  }
  
  .title {
    color: var(--gray-500, #282828);
    font-family: Elza;
    font-size: 46px;
    font-style: normal;
    font-weight: 700;
    line-height: 130%;
    letter-spacing: 0.62px;
  }
  
  .tigerTransparent {
    width: 80px;
    height: auto;
    float: none;
    margin-top: 20px;
    display: block;
    margin-left: auto;
    margin-right: auto;
  }
  
  @media (max-width: 768px) {
      .ProgramCalendarPageViewContainer {
        padding: 0px 20px;
      }
  
      .title {
        font-size: 35px !important;
      }
  
      .tigerTransparent {
          width: 60px; 
      }
  }
  </style>
  
  