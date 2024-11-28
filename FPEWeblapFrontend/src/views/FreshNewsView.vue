<template>
    <div>
        <b-container fluid :class="{'FreshNewsViewContainer': !isPage, 'FreshNewsViewContainerPage': isPage}">
            <b-row v-if="!isPage">
                <b-col>
                    <p class="FreshNewsViewTitle">Legfrissebb híreink</p>
                </b-col>
            </b-row>
            <b-row class="cardsFrame">
                <b-col :cols="isMobile ? '12' : '4'" v-for="news, index in newsData" :key="index">
                    <div class="centering-wrapper">
                        <FPECardImgTop :title="news.title"
                                       :bodyText="news.text"
                                       :imgSrc="news.imageUrl == null || news.imageUrl =='' || news.imageUrl == undefined ? imgSrc : news.imageUrl"
                                       :facebookLink="news.facebookLink"/>
                    </div>
                </b-col>
            </b-row>
            <b-row align-h="center" v-if="isLoading">
                <b-col cols="12" class="center-content">
                    <div class="lds-ellipsis"><div></div><div></div><div></div><div></div></div>
                    <!-- Replace the paragraph with an image or icon if you prefer -->
                </b-col>
            </b-row>
        </b-container>
        <b-row v-if="!isPage" align-h="center" >
            <b-col class="showAllButtonFrame">
                <router-link to="/news">
                    <div class="centering-wrapper">
                        <button class="showAllButton">Az összes megtekintése</button>
                    </div>
                </router-link>
            </b-col>
        </b-row>
        <b-row align-h="center" v-if="!isPage"> 
            <b-col cols="1" class="center-content">
                <img class="tigerTransparent" src="../assets/TigerTransparent.svg" alt="tigerTransparent">
            </b-col>
        </b-row> 
    </div>
</template>

<script>
import FPECardImgTop from '@/components/FPECardImgTop.vue'
import Api from '../util/Api.js'
import { deviceMixin } from '@/deviceMixin';

export default {
    name: 'FreshNewsView',
    components: {
        FPECardImgTop,
    },
    mixins: [deviceMixin],
    props: {
        isPage: {
            type: Boolean,
            default: false,
        },
    },
    data() {
        return {
            newsData: [],
            changeHappend: true,
            imgSrc: require('@/assets/85effbc9c8c3c5a54602bcc4b2620abf.png'),
            paginationParams: {
                params: {
                    pageCount:1, 
                    pageSize:6
                }
            },
            isLoading: false,
        };
    },
    beforeMount() {
        this.paginationParams = {
                params: {
                    pageCount:1, 
                    pageSize:6
                }
        }
        this.getNews(this.paginationParams);
    },
    mounted() {
        if(this.isPage){
            this.getNextNews();
        }
    },
    methods: {
        getNews(params) {
            Api.getNews(params).then((response) => {
                this.newsData = response.data;
            }).catch((error) => {
                console.log(error);
            });
        },
        getNextNews() {
            if(this.isPage){
                window.onscroll = () => {
                    let scrollPosition = document.documentElement.scrollTop + window.innerHeight;
                    let offsetHeight = document.documentElement.offsetHeight;
                    let threshold = 20;

                    if (scrollPosition >= offsetHeight - threshold) {
                        this.isLoading = true; 
                        this.paginationParams.params.pageCount ++
                        Api.getNews(this.paginationParams).then(response => {
                            setTimeout(() => {
                                if(this.changeHappend){
                                    this.changeHappend = false;
                                    response.data.forEach((element) => {
                                        let isIncluded = false;   
                                        this.newsData.forEach((news) => {
                                            if(news.id == element.id){
                                                isIncluded = true;
                                            }
                                        });
                                        if(!isIncluded){
                                            this.changeHappend = true;
                                            this.newsData.push(element);
                                        }
                                    });
                                }
                                this.isLoading = false; 
                            }, 1000)
                        }).catch(error => {
                            console.error( error);
                            this.isLoading = false; 
                        });
                    }
                }
            }
        }
    },
}
</script>

<style scoped>
.centering-wrapper {
    display: flex;
    justify-content: center;
    width: 100%;
}
.FreshNewsViewContainer{
    padding: 60px 200px 30px 200px;
    align-items: center;
    gap: 50px;
    width: 1440px;
    min-width: 422px;
}
.FreshNewsViewContainerPage{
    display: flex;
    width: 1440px;
    padding: 70px 200px;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 50px;
}
.previouseNewsButtonWraper{
    display: flex;
    align-items: center;
    gap: 40px;
}
.previouseNewsButton{
    color: var(--Black, #0F202E);
    text-align: center;
    font-family: Elza;
    font-size: 16px;
    font-style: normal;
    font-weight: 600;
    line-height: normal;
    letter-spacing: 0.16px;
    border-bottom: 2px solid var(--Blue-Primary, #6CC8F9);
}
.previouseNewsButton:hover{
    border-bottom: 2px solid var(--Yellow-Primary, #F4BC43);
    cursor: pointer;
}
.newNewsButton{
        color: var(--Black, #0F202E);
        text-align: center;
        font-family: Elza;
        font-size: 16px;
        font-style: normal;
        font-weight: 600;
        line-height: normal;
        letter-spacing: 0.16px;
        border-bottom: 2px solid var(--Blue-Primary, #6CC8F9);
    }
.newNewsButton:hover{
    border-bottom: 2px solid var(--Yellow-Primary, #F4BC43);
    cursor: pointer;
}
@media (max-width: 768px) {
    .FreshNewsViewContainer, .FreshNewsViewContainerPage {
        padding: 20px;
        width: auto;
        min-width: 0;
        max-width: 100%;
    }
    .FreshNewsViewTitle {
        font-size: 35px !important;
        font-weight: 700;
    }
    .tigerTransparent {
        width: 56px;
        height: 45.5px;
    }
}
.center-content {
    display: flex;
    justify-content: center;
}
.cardsFrame {
    row-gap: 16px;
}
.FreshNewsViewTitle {
    color: var(--gray-500, #282828);
    font-family: Elza;
    font-size: 46px;
    line-height: 150%;
    letter-spacing: -0.69px;
}
.tigerTransparent {
    width: 112.829px;
    height: 91.061px;
}
.showAllButton, .showAllButton:hover {
    padding: 8px 20px; 
    font-size: 14px;
}
.showAllButton{
    margin-bottom: 20px;
    display: flex;
    height: 36px;
    padding: 8px 32px;
    justify-content: center;
    align-items: center;
    border-radius: 50px;
    background: var(--Blue-Primary, #6CC8F9);
    color: var(--Black, #0F202E);
    text-align: center;
    font-family: Circular Std;
    font-size: 16px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    border: none;
}
.showAllButton:hover{
    margin-bottom: 20px;
    display: inline-flex;
    height: 36px;
    padding: 8px 32px;
    justify-content: center;
    align-items: center;
    gap: 4px;
    flex-shrink: 0;
    border-radius: 50px;
    background: var(--Yellow-Primary, #F4BC43);
    box-shadow: 0px 4px 15px 0px rgba(29, 39, 92, 0.20);
    color: var(--Black, #0F202E);
    text-align: center;
    font-family: Circular Std;
    font-size: 16px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
}
.showAllButtonFrame a {
    text-decoration: none;
}

.lds-ellipsis {
  /* change color here */
  color: #1c4c5b
}
.lds-ellipsis,
.lds-ellipsis div {
  box-sizing: border-box;
}
.lds-ellipsis {
  display: inline-block;
  position: relative;
  width: 80px;
  height: 80px;
}
.lds-ellipsis div {
  position: absolute;
  top: 33.33333px;
  width: 13.33333px;
  height: 13.33333px;
  border-radius: 50%;
  background: currentColor;
  animation-timing-function: cubic-bezier(0, 1, 1, 0);
}
.lds-ellipsis div:nth-child(1) {
  left: 8px;
  animation: lds-ellipsis1 0.6s infinite;
}
.lds-ellipsis div:nth-child(2) {
  left: 8px;
  animation: lds-ellipsis2 0.6s infinite;
}
.lds-ellipsis div:nth-child(3) {
  left: 32px;
  animation: lds-ellipsis2 0.6s infinite;
}
.lds-ellipsis div:nth-child(4) {
  left: 56px;
  animation: lds-ellipsis3 0.6s infinite;
}
@keyframes lds-ellipsis1 {
  0% {
    transform: scale(0);
  }
  100% {
    transform: scale(1);
  }
}
@keyframes lds-ellipsis3 {
  0% {
    transform: scale(1);
  }
  100% {
    transform: scale(0);
  }
}
@keyframes lds-ellipsis2 {
  0% {
    transform: translate(0, 0);
  }
  100% {
    transform: translate(24px, 0);
  }
}
</style>
