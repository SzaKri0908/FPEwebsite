<template>
  <div style="background: var(--Light-Grey, #F5F5F5);">
    <b-container fluid class="BannerViewContainer" v-if="!isMobile">
        <b-row>
            <b-col cols="3" v-for="count in cardCount" :key="count">
                <BannerCard :imgSrc="imgSrcData[count - 1]"
                            :title="titleData[count - 1]"
                            :router="routerData[count - 1]"/>
            </b-col>
        </b-row>
    </b-container>
    <b-container fluid v-else class="BannerViewContainerMobile">
        <b-row v-for="count in cardCount" :key="count" class="justify-content-center">
            <b-col cols="12" class="d-flex justify-content-center">
                <BannerCard :imgSrc="imgSrcData[count - 1]"
                            :title="titleData[count - 1]"
                            :router="routerData[count - 1]"/>
            </b-col>
        </b-row>
    </b-container>
  </div>
</template>

<script>
import BannerCard from '@/components/BannerCard.vue'
import { deviceMixin } from '@/deviceMixin';

export default {
  name: 'BannerView',
    components: {
        BannerCard,
    },
    mixins: [deviceMixin],
    data() {
        return {
            cardCount: 4,
            imgSrcData: [
                require('@/assets/airsoft5.png'),
                require('@/assets/gyermekotthon_1.png'),
                require('@/assets/4.png'),
                require('@/assets/FiatalokPécsértEgyesület.png'),
            ],
            titleData: [
                'Programok',
                'Galléria',
                'Hírek',
                'Kapcsolat',
            ],
            routerData: [
                '/programs',
                '/gallery',
                '/news',
                '/contact',
            ],
        };
    },
 
}
</script>

<style scoped>
     .BannerViewContainer {
        padding: 60px 200px 30px 200px;
        align-items: center;
        gap: 50px;
        width: 1440px;
        min-width: 422px;
    }
    .BannerViewContainerMobile {
        padding: 20px;
        width: auto;
        min-width: 0;
        max-width: 100%;
    }
</style>
