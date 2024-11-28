<template>
    <div class="FPEMottoViewContainerWraper">
      <div class="FPEMottoViewContainer">
        <b-row>
          <b-col>
            <img src="../assets/Quote.svg" alt="Quote" class="Quote">
          </b-col>
        </b-row>
        <b-row>
          <b-col>
            <p class="heading1">
              Fiatalok Pécsért
            </p>
            <p class="heading2">
              A jövőnkért, a városunkért! 
            </p>
            <p class="motto">
              Fiatalok Pécsért Egyesület mottója
            </p>
          </b-col>
          <b-col>
            <img src="../assets/TigerBack.svg" alt="tiger" class="mottoTiger">
          </b-col>
        </b-row>
      </div>
    </div>
  </template>
  
  <style lang="scss" scoped>
    .FPEMottoViewContainerWraper {
      background: var(--Blue-Light, #EDF9FF);
      box-shadow: 0px 8px 15px 0px rgba(29, 39, 92, 0.20);
    }
    .FPEMottoViewContainer {
      padding-left: 200px;
      margin-left: 39.6px;
    }
    .Quote {
      width: 62px;
      height: 50px;
      flex-shrink: 0;
      fill: #FFF;
    }
    .heading1 {
      margin-top: 46px;  
      color: var(--Blue-Primary, #6CC8F9);
      font-family: Elza;
      font-size: 46px;
      font-style: normal;
      font-weight: 600;
      line-height: 150%;
      letter-spacing: -0.69px;
    }
    .heading2 {
      color: var(--Black, #0F202E);
      font-family: Elza;
      font-size: 46px;
      font-style: normal;
      font-weight: 600;
      line-height: 150%;
      letter-spacing: -0.69px;
    }
    .motto {
      width: 679px;
      color: var(--gray-500, #282828);
      font-family: Elza;
      font-size: 18px;
      font-style: normal;
      font-weight: 400;
      line-height: 150%;
      letter-spacing: 0.18px;
    }
    .mottoTiger {
      width: 497.315px;
      height: 297px;
      float: right;
    }
  </style>
