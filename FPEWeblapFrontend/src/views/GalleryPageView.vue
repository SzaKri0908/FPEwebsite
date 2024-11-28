<template>
    <div>
        <b-container class="GalleryPageViewContainer">
            <b-row>
                <b-col :cols="isMobile ? '9' : '10'">
                    <p class="title">Rendezvényeink galériája</p>
                </b-col>
                <b-col :cols="isMobile ? '3' : '2'">
                    <img class="TigerFullHead" src="../assets/TigerFullHead.svg" alt="TigerFullHead">
                </b-col>
            </b-row>
        </b-container>
        <GalleryView/>
    </div>
</template>
  
  <script>
    import GalleryView from './GalleryView.vue'
    import { deviceMixin } from '@/deviceMixin';
  
    export default {
        name: 'GalleryPageView',
        mixins: [deviceMixin],
        components: {
            GalleryView,
        },
    }
  </script>
  
  <style scoped>
        .GalleryPageViewContainer {
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
        .TigerFullHead {
            width: 140px;
            height: auto;
            float: none;
            margin-top: 20px;
            display: block;
            margin-left: auto;
            margin-right: auto;
        }

        @media (max-width: 768px) {
            .GalleryPageViewContainer {
                padding: 0px 20px;
            }
            .title {
                font-size: 25px !important;
            }
            .TigerFullHead {
                width: 80px; 
            }
        }
  </style>
  