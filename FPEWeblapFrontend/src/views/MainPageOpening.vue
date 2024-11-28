<template>
  <div>
    <div class="main-view-container" v-if="!isMobile">
      <div class="frame">
        <p class="heading">Fiatalok Pécsért</p>
        <div class="child-heading">
          <p class="text-wrapper">
            A jövőnkért, a városunkért! 
          </p>
          <!-- <p class="p">
            Lorem ipsum dolor si amet consecteur dolor sin
            expecteur dolor sin loorem ipsum.
          </p> -->
          <router-link to="/contact">
            <button class="moreButton">Tovább</button>
          </router-link>
        </div>
      </div>
      <div>
        <img class="picture" alt="Picture" src="../assets/Picture.png"/>
        <img class="tiger" alt="Tiger" src="../assets/Tiger.svg"/>
      </div>
    </div>
    <b-container v-else>
      <b-row>
          <b-col class="textFrame">
              <p class="title">Fiatalok Pécsért</p>
              <p class="textBody">
                A jövőnkért, a városunkért! 
              </p>
              <!-- <p class="textBody">
                Lorem ipsum dolor si amet consecteur dolor sin
                expecteur dolor sin loorem ipsum.
              </p> -->
          </b-col>          
      </b-row>
      <b-row align-h="center">
        <b-col class="center-content">
          <router-link to="/contact">
            <button class="moreButton">Tovább</button>
          </router-link>
        </b-col>
      </b-row>
      <b-row class="justify-content-center">
          <b-col cols="12" class="text-center">
              <img alt="Picture" src="../assets/Picture.png" class="mainImage mx-auto d-block"/>
          </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
  import { deviceMixin } from '@/deviceMixin';
  export default {
      name: 'MainPageOpening',
      mixins: [deviceMixin],
  }
</script>

<style lang="scss" scoped>

.main-view-container {
  display: flex;
  align-items: center;
  position: relative;
  width: 1440px;
  margin: 0 auto; // Centers horizontally within its parent
  padding: 100px 0px 120px 180px; 
}
.main-view-container .frame {
  align-items: flex-start;
  display: inline-flex;
  flex: 0 0 auto;
  flex-direction: column;
  gap: 50px;
  position: relative;
}
.main-view-container .heading {
  width: 688px;
  color: var(--gray-500, #282828);
  font-family: Elza;
  font-size: 62px;
  font-style: normal;
  font-weight: 700;
  line-height: 130%;
  letter-spacing: 0.62px;
  margin-bottom: 20px;
  margin-left: 20px;
}
.main-view-container .child-heading {
  align-items: flex-start;
  display: flex;
  flex: 0 0 auto;
  flex-direction: column;
  gap: 1.5px;
  position: relative;
  width: 424px;
}
.main-view-container .child-heading a {
  text-decoration: none;
}

.main-view-container .text-wrapper {
  color: var(--gray-500, #282828);
  font-family: Poppins;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  line-height: 130%; 
  letter-spacing: 0.24px;
  margin-bottom: 20px;
  margin-left: 20px;
}

.main-view-container .p {
  color: var(--gray-500, #282828);
  font-family: Elza;
  font-size: 18px;
  font-style: normal;
  font-weight: 400;
  line-height: 150%;
  letter-spacing: 0.18px;
  margin-bottom: 20px;
  margin-left: 20px;
}

.main-view-container .picture {
  height: 429px;
  left: 720px;
  position: absolute;
  top: 100px;
  width: 511px;
}

.main-view-container .tiger {
  height: 137px;
  left: 830px;
  position: absolute;
  top: 287px;
  width: 216px;
}
.moreButton{
  margin-bottom: 20px;
  margin-left: 20px;
  display: flex;
  height: 36px;
  padding: 8px 32px;
  justify-content: center;
  align-items: center;
  gap: 4px;
  border-radius: 50px;
  border: 2px solid var(--Blue-Primary, #6CC8F9);
  background: #FFF;
  color: var(--Black, #0F202E);
  text-align: center;
  font-family: Circular Std;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
}
.moreButton:hover{
  margin-bottom: 20px;
  margin-left: 20px;
    display: flex;
    height: 36px;
    padding: 8px 32px;
    justify-content: center;
    align-items: center;
    gap: 4px;
    flex-shrink: 0;
    border-radius: 50px;
    border: 2px solid var(--Yellow-Primary, #F4BC43);
    background: #FFF;
    box-shadow: 0px 4px 15px 0px rgba(29, 39, 92, 0.20);
    color: var(--Black, #0F202E);
    text-align: center;
    font-family: Circular Std;
    font-size: 16px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
}
.moreButton, .moreButton:hover {
  margin: 20px 20px 20px 0; 
  padding: 8px 16px;
}
.textFrame {
    width: 100%; 
    align-items: center; 
}
.title, .textBody{
  text-align: center;
  padding: 0 10px; 
}

.mainImage {
    width: 100%; 
    height: auto; 
    max-width: 422px; 
    margin-top: 20px; 
}
.title{
    align-self: stretch;
    color: var(--Black, #0F202E);
    font-family: Poppins;
    font-size: 24px;
    font-style: normal;
    font-weight: 600;
    line-height: 130%;
    letter-spacing: 0.24px;
}
.textBody{
    align-self: stretch;
    color: var(--colors-greyscale-dark, #464646);
    font-family: Elza;
    font-size: 18px;
    font-style: normal;
    font-weight: 400;
    line-height: 150%;
    letter-spacing: 0.18px;
}
.center-content {
      display: flex;
      justify-content: center;
  }
</style>