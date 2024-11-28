<template>
    <div style="background: var(--Light-Grey, #F5F5F5);">
        <b-container fluid class="GalleryViewContainer">
            <div v-for="gallery, index in galleriesData" :key="index">
                <b-row>
                    <b-col  class="GalleryViewTitle">
                        {{ gallery.title }}
                    </b-col>
                </b-row>
                <b-row>
                    <b-col :cols="isMobile ? '12' : '4'" class="text-center d-flex justify-content-center" v-for="child, index in gallery.child" :key="index">
                        <b-link :href="child.facebook_link" target="_blank" class="d-inline-block">
                            <ImageFrame :imgSrc="child.imgUrl"/>
                        </b-link>
                    </b-col>
                </b-row>
                <b-row align-h="center" v-if="isLoading">
                    <b-col cols="12" class="center-content">
                        <div class="lds-ellipsis"><div></div><div></div><div></div><div></div></div>
                        <!-- Replace the paragraph with an image or icon if you prefer -->
                    </b-col>
                </b-row>
            </div>
        </b-container>
    </div>
</template>

<script>
    import ImageFrame from '@/components/ImageFrame.vue'
    import { deviceMixin } from '@/deviceMixin';
    import Api from '../util/Api.js'

    export default {
        name: 'GalleryView',
        mixins: [deviceMixin],
        components: {
            ImageFrame,
        },
        data() {
            return {
                galleriesData: [],
                changeHappend: true,
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
            this.getGallery(this.paginationParams);
        },
        mounted() {
            this.getNextGallery();
        },
        methods: {
            getGallery(params) {
                Api.getGallery(params).then((response) => {
                    this.galleriesData = response.data;
                }).catch((error) => {
                    console.log(error);
                });
            },
            getNextGallery() {
                window.onscroll = () => {
                    let scrollPosition = document.documentElement.scrollTop + window.innerHeight;
                    let offsetHeight = document.documentElement.offsetHeight;
                    let threshold = 20;

                    if (scrollPosition >= offsetHeight - threshold) {
                        this.isLoading = true; 
                        this.paginationParams.params.pageCount ++
                        Api.getGallery(this.paginationParams).then(response => {
                            setTimeout(() => {
                                if(this.changeHappend){
                                    this.changeHappend = false;
                                    response.data.forEach((element) => {
                                        let isIncluded = false;   
                                        this.galleriesData.forEach((gallery) => {
                                            if(gallery.id == element.id){
                                                isIncluded = true;
                                            }
                                        });
                                        if(!isIncluded){
                                            this.changeHappend = true;
                                            this.galleriesData.push(element);
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
    }
</script>

<style scoped>
    .center-content {
        display: flex;
        justify-content: center;
    }
        .GalleryViewContainer{
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            width: 100%;
            max-width: 1029px;
            gap: 50px;
            padding: 40px 20px;
            margin: 0 auto;
        }
    .GalleryViewTitle{
        text-align: center;
        color: var(--Gray-500, #282828);
        font-family: Poppins;
        font-size: 30px;
        line-height: 130%;
        letter-spacing: 0.24px;
        width: 100%;
    }
    @media (max-width: 768px) {
        .GalleryViewTitle {
            font-size: 25px;
        }
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

