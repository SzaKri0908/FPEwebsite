<template>
    <div style="background: var(--Light-Grey, #F5F5F5);">
        <b-container fluid :class="{'ProgramCalendarViewContainer': !isPage, 'ProgramCalendarViewContainerPage': isPage}">
            <b-row>
                <b-col v-if="!isPage">
                    <p class="ProgramCalendarViewTitle">Programnaptár</p>
                </b-col>
                <b-col v-if="isPage">
                    <p class="ProgramCalendarViewTitleProgramMonth">{{ programMonth.toLocaleString('hu-HU', monthOptions) }}</p>
                </b-col>
            </b-row>
            <div class="nextOrPreviouseProgramButtonWraper" v-if="isPage">
                <p class="previouseProgramButton" @click="showPreviouseMonth(),scrollToTop()">
                    <svg xmlns="http://www.w3.org/2000/svg" width="21" height="20" viewBox="0 0 21 20" fill="none">
                        <path d="M9.69202 7.10863C9.9361 6.86455 9.9361 6.46882 9.69202 6.22475C9.44795 5.98067 9.05222 5.98067 8.80814 6.22475L5.47481 9.55808C5.35277 9.68012 5.29175 9.84007 5.29175 10C5.29175 10.0848 5.30861 10.1656 5.33917 10.2393C5.36967 10.313 5.41488 10.382 5.47481 10.442L8.80814 13.7753C9.05222 14.0194 9.44795 14.0194 9.69202 13.7753C9.9361 13.5312 9.9361 13.1355 9.69202 12.8914L7.42563 10.625H15.5001C15.8453 10.625 16.1251 10.3452 16.1251 10C16.1251 9.65484 15.8453 9.37502 15.5001 9.37502H7.42563L9.69202 7.10863Z" fill="#2D2D2D"/>
                    </svg>
                    Előző hónap
                </p>
                <p class="nextProgramButton" @click="showNextMonth(),scrollToTop()">
                    Következő hónap
                    <svg xmlns="http://www.w3.org/2000/svg" width="21" height="20" viewBox="0 0 21 20" fill="none">
                        <path d="M11.7248 7.10863C11.4807 6.86455 11.4807 6.46882 11.7248 6.22475C11.9689 5.98067 12.3646 5.98067 12.6087 6.22475L15.942 9.55808C16.1861 9.80216 16.1861 10.1979 15.942 10.442L12.6087 13.7753C12.3646 14.0194 11.9689 14.0194 11.7248 13.7753C11.4807 13.5312 11.4807 13.1355 11.7248 12.8914L13.9912 10.625H5.91675C5.57157 10.625 5.29175 10.3452 5.29175 10C5.29175 9.65484 5.57157 9.37502 5.91675 9.37502H13.9912L11.7248 7.10863Z" fill="#2D2D2D"/>
                    </svg>
                </p>
            </div>
            <b-row  class="cardsFrame">
                <b-col :cols="isMobile ? '12' : '4'" class="text-center" v-for="program, index in programsData" :key="index">
                    <div class="centering-wrapper">
                        <FPECardImgBottom :title="new Date(program.eventDate).toLocaleString('hu-HU', dateOptions)"
                                          :bodyText="program.text"
                                          :imgSrc="program.imageUrl == null || program.imageUrl =='' || program.imageUrl == undefined ? imgSrc : program.imageUrl"
                                          :facebookEventLink="program.facebookEventLink"/>
                    </div>
                </b-col>
            </b-row>
            <b-row align-h="center" v-if="isLoading">
                <b-col cols="12" class="center-content">
                    <div class="lds-ellipsis"><div></div><div></div><div></div><div></div></div>
                </b-col>
            </b-row>
            <b-row v-if="!isPage">
                <b-col class="showAllButtonFrame">
                    <router-link to="/programs">
                        <div class="centering-wrapper">
                            <button class="showAllButton">Az összes megtekintése</button>
                        </div>
                    </router-link>
                </b-col>
            </b-row>
            <div class="nextOrPreviouseProgramButtonWraper" v-if="isPage">
                <p class="previouseProgramButton" @click="showPreviouseMonth(),scrollToTop()">
                    <svg xmlns="http://www.w3.org/2000/svg" width="21" height="20" viewBox="0 0 21 20" fill="none">
                        <path d="M9.69202 7.10863C9.9361 6.86455 9.9361 6.46882 9.69202 6.22475C9.44795 5.98067 9.05222 5.98067 8.80814 6.22475L5.47481 9.55808C5.35277 9.68012 5.29175 9.84007 5.29175 10C5.29175 10.0848 5.30861 10.1656 5.33917 10.2393C5.36967 10.313 5.41488 10.382 5.47481 10.442L8.80814 13.7753C9.05222 14.0194 9.44795 14.0194 9.69202 13.7753C9.9361 13.5312 9.9361 13.1355 9.69202 12.8914L7.42563 10.625H15.5001C15.8453 10.625 16.1251 10.3452 16.1251 10C16.1251 9.65484 15.8453 9.37502 15.5001 9.37502H7.42563L9.69202 7.10863Z" fill="#2D2D2D"/>
                    </svg>
                    Előző hónap
                </p>
                <p class="nextProgramButton" @click="showNextMonth(),scrollToTop()">
                    Következő hónap
                    <svg xmlns="http://www.w3.org/2000/svg" width="21" height="20" viewBox="0 0 21 20" fill="none">
                        <path d="M11.7248 7.10863C11.4807 6.86455 11.4807 6.46882 11.7248 6.22475C11.9689 5.98067 12.3646 5.98067 12.6087 6.22475L15.942 9.55808C16.1861 9.80216 16.1861 10.1979 15.942 10.442L12.6087 13.7753C12.3646 14.0194 11.9689 14.0194 11.7248 13.7753C11.4807 13.5312 11.4807 13.1355 11.7248 12.8914L13.9912 10.625H5.91675C5.57157 10.625 5.29175 10.3452 5.29175 10C5.29175 9.65484 5.57157 9.37502 5.91675 9.37502H13.9912L11.7248 7.10863Z" fill="#2D2D2D"/>
                    </svg>
                </p>
            </div>
        </b-container>
    </div>
</template>

<script>
    import FPECardImgBottom from '@/components/FPECardImgBottom.vue'
    import Api from '../util/Api.js'
    import { deviceMixin } from '@/deviceMixin';

    export default {
        name: 'ProgramCalendarView',
        components: {
            FPECardImgBottom,
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
                programsData: [],
                changeHappend: true,
                imgSrc: require('@/assets/871f252aca2bd0839c8f62298afa6085.png'),
                paginationParams: {
                    params: {
                        pageCount:1,
                        pageSize:6,
                        date: this.$moment(new Date()).format("YYYY-MM-DD")
                    }
                },
                dateOptions: {
                    year: 'numeric',
                    month: 'long',
                    day: '2-digit',
                    timeZone: 'Europe/Budapest'
                },
                monthOptions: {
                    year: 'numeric',
                    month: 'long',
                    timeZone: 'Europe/Budapest'
                },
                programMonth: new Date(),
                isLoading: false,
            };
        },
        created() {
            this.getPrograms(this.paginationParams);
        },
        beforeMount() {
            this.paginationParams = {
                params: {
                        pageCount:1,
                        pageSize:6,
                        date: this.$moment(new Date()).format("YYYY-MM-DD")
                    }
            }
            this.getPrograms(this.paginationParams);
        },
        mounted() {
            if(this.isPage){
                this.getNextPrograms();
            }
        },
        methods: {
            getPrograms(params) {
                Api.getEvents(params).then((response) => {
                    this.programsData = response.data.rows;
                    this.programMonth = new Date(this.programsData[0].eventDate);
                }).catch((error) => {
                    console.log(error);
                });
            },
            getNextPrograms() {
                if(this.isPage){
                    window.onscroll = () => {
                        let scrollPosition = document.documentElement.scrollTop + window.innerHeight;
                        let offsetHeight = document.documentElement.offsetHeight;
                        let threshold = 20;

                        if (scrollPosition >= offsetHeight - threshold) {
                            this.isLoading = true; 
                            this.paginationParams.params.pageCount ++;
                            Api.getEvents(this.paginationParams).then(response => {
                                setTimeout(() => {
                                    if(this.changeHappend){
                                        this.changeHappend = false;
                                        response.data.rows.forEach((element) => {
                                                let isIncluded = false;
                                                this.programsData.forEach((program) => {
                                                    if(program.id == element.id){
                                                        isIncluded = true;
                                                    }
                                                });
                                                if(!isIncluded){
                                                    this.changeHappend = true;
                                                    this.programsData.push(element);
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
            },
            showPreviouseMonth(){
                if(this.$moment(this.paginationParams.params.date).subtract(1, 'months').format("YYYY-MM-DD") < this.$moment(new Date()).format("YYYY-MM-DD")){
                    return;
                }else{
                    this.paginationParams.params.date = this.$moment(this.paginationParams.params.date).subtract(1, 'months').format("YYYY-MM-DD");
                    this.paginationParams.params.pageSize = 6;
                    this.getPrograms(this.paginationParams);
                }
            },
            showNextMonth(){
                this.paginationParams.params.date = this.$moment(this.paginationParams.params.date).add(1, 'months').format("YYYY-MM-DD");
                this.paginationParams.params.pageSize = 6;
                this.getPrograms(this.paginationParams);
            },
            scrollToTop() {
                window.scrollTo({
                    top: 170,
                    left: 0,
                    behavior: 'smooth' 
                });
            },
        },
        computed: {
        },
    }
</script>

<style scoped>
.cardsFrame {
    row-gap: 16px;
}
.centering-wrapper {
    display: flex;
    justify-content: center;
    width: 100%;
}
.ProgramCalendarViewContainer {
    padding: 60px 200px 30px 200px;
    align-items: center;
    gap: 50px;
    width: 1440px;
    min-width: 422px;
    max-width: 1440px;
}
.ProgramCalendarViewContainerPage{
    display: flex;
    width: 1440px;
    padding: 70px 200px;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 50px;
}
.ProgramCalendarViewTitle {
    color: var(--gray-500, #282828);
    font-family: Elza;
    font-size: 46px;
    line-height: 150%;
    letter-spacing: -0.69px;
}
.ProgramCalendarViewTitleProgramMonth {
    color: var(--gray-500, #282828);
    font-family: Elza;
    font-size: 40px !important;
    line-height: 150%;
    letter-spacing: -0.69px;
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
.nextOrPreviouseProgramButtonWraper{
    display: flex;
    align-items: center;
    gap: 40px;
}
.previouseProgramButton{
    color: var(--Black, #0F202E);
    text-align: center;
    font-family: Elza;
    font-size: 20px;
    font-style: normal;
    font-weight: 600;
    line-height: normal;
    letter-spacing: 0.16px;
    border-bottom: 2px solid var(--Blue-Primary, #6CC8F9);
}

.previouseProgramButton:hover{
    border-bottom: 2px solid var(--Yellow-Primary, #F4BC43);
    cursor: pointer;
}

.nextProgramButton{
    color: var(--Black, #0F202E);
    text-align: center;
    font-family: Elza;
    font-size: 20px;
    font-style: normal;
    font-weight: 600;
    line-height: normal;
    letter-spacing: 0.16px;
    border-bottom: 2px solid var(--Blue-Primary, #6CC8F9);
}

.nextProgramButton:hover{
    border-bottom: 2px solid var(--Yellow-Primary, #F4BC43);
    cursor: pointer;
}
@media (max-width: 768px) {
    .ProgramCalendarViewContainer, .ProgramCalendarViewContainerPage {
        padding: 20px;
        width: auto;
        min-width: 0;
        max-width: 100%;
    }
    .ProgramCalendarViewTitle {
        font-size: 35px !important;
        font-weight: 700;
    }
    .previouseProgramButton, .nextProgramButton {
        font-size: 16px; 
        padding: 0 10px;
    }
    .ProgramCalendarViewTitleProgramMonth {
        font-size: 32px !important;
    }
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
