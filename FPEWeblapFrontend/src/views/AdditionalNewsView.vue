<template>
    <div>
        <b-container fluid class="AdditionalNewsViewContainer"  v-if="!isMobile">
            <b-row>
                <b-col class="textFrame" cols="7">
                    <p class="title">Hol a határ? – Egészség egy rohanó világban</p>
                    <p class="textBody"> 
                        A programunk célja az volt, hogy felhívja a figyelmet az egészség fontosságára egy olyan világban, ahol a sietség és a stressz mindennapos kihívásokat jelent. Itt kiemelten fontosnak tartottuk, hogy szó essen a rendszeres sportról, és a szabad levegőn végzett testmozgásról. A program keretében Zobák-aknára látogattunk, ahol a résztvevők betekintést nyerhettek és kipróbálhattak egy olyan sportot, amire a hétköznapokban valószínűleg nem sok lehetőségünk lenne. Az egész napos airsoft-játék során amellett, hogy igazán megmozgathatták magukat, a csapatokban végzett kihívások közepette a  kommunikációs és interperszonális készségeiket is fejleszthették a játékosok.
                    </p>
                    <hr/>
                    <p  class="textBody">
                        A program a Fiatalok Pécsért Egyesület 'Generation Now' (NEAO-KP-1-2023/8-001407) projektje keretében, a Nemzeti Együttműködési Alap pályázatának támogatásával valósult meg.
                    </p>
                    <b-link :href="'https://www.facebook.com/fiatalokpecsert/posts/pfbid0b6pGBJGA1QcepefYZsRgexesbY6rWLSaPptQwTZLM8NiHJ4onoHTJw2MTi3Vfri2l'" target="_blank">
                        <button class="moreButton">Tovább</button>
                    </b-link>
                </b-col>
                <b-col class="ml-5">
                    <img src="../assets/airsoft2.png" alt="airsoft" class="AdditionalNewsImage">
                </b-col>
            </b-row>
        </b-container>
        <b-container v-else>
            <b-row>
                <b-col class="textFrame">
                    <p class="title">Hol a határ? – Egészség egy rohanó világban</p>
                    <p class="textBody">
                        A programunk célja az volt, hogy felhívja a figyelmet az egészség fontosságára egy olyan világban, ahol a sietség és a stressz mindennapos kihívásokat jelent. Itt kiemelten fontosnak tartottuk, hogy szó essen a rendszeres sportról, és a szabad levegőn végzett testmozgásról. A program keretében Zobák-aknára látogattunk, ahol a résztvevők betekintést nyerhettek és kipróbálhattak egy olyan sportot, amire a hétköznapokban valószínűleg nem sok lehetőségünk lenne. Az egész napos airsoft-játék során amellett, hogy igazán megmozgathatták magukat, a csapatokban végzett kihívások közepette a  kommunikációs és interperszonális készségeiket is fejleszthették a játékosok.
                    </p>
                    <hr/>
                    <p  class="textBody">
                        A program a Fiatalok Pécsért Egyesület 'Generation Now' (NEAO-KP-1-2023/8-001407) projektje keretében, a Nemzeti Együttműködési Alap pályázatának támogatásával valósult meg.
                    </p>
                    <div class="center-content">
                        <b-link :href="'https://www.facebook.com/fiatalokpecsert/posts/pfbid0b6pGBJGA1QcepefYZsRgexesbY6rWLSaPptQwTZLM8NiHJ4onoHTJw2MTi3Vfri2l'" target="_blank">
                            <button class="moreButton">Tovább</button>
                        </b-link>
                    </div>
                </b-col>          
            </b-row>
            <b-row class="justify-content-center">
                <b-col cols="12" class="text-center">
                    <img src="../assets/airsoft2.png" alt="airsoft" class="AdditionalNewsImage mx-auto d-block">
                </b-col>
            </b-row>
        </b-container>
        <b-row align-h="center"> 
            <b-col cols="1" class="center-content">
                <img class="tigerTransparent" src="../assets/TigerTransparent.svg" alt="tigerTransparent">
            </b-col>
        </b-row>
    </div>
</template>

<script>
    import { deviceMixin } from '@/deviceMixin';
    export default {
        name: 'AdditionalNewsView',
        mixins: [deviceMixin],

    }
</script>

<style scoped>
    .AdditionalNewsViewContainer{
        display: flex;
        width: 1440px;
        min-width: 422px;
        max-width: 1440px;
        padding: 120px 200px;
        align-items: flex-start;
        align-content: flex-start;
        gap: 24px;
        flex-wrap: wrap;
    }
    .tigerTransparent{
        width: 112.829px;
        height: 91.061px;
    }
    .textFrame{
        align-items: flex-start;
        gap: 16px;
        border-radius: 2px;
        margin-right: 24px;
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
    .moreButton{
        display: flex;
        height: 36px;
        padding: 8px 32px;
        justify-content: center;
        align-items: center;
        gap: 4px;
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
    .moreButton:hover{
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
    .AdditionalNewsImage{
        height: 276px;  
        flex: 1 0 0;
        border-radius: 24px;
    }
    .center-content {
        display: flex;
        justify-content: center;
    }
    @media (max-width: 768px) {
    .AdditionalNewsViewContainer {
        align-items: center;
        padding: 5px;
        gap: 16px;
    }
    .textFrame {
        width: 100%; 
        margin-right: 0; 
        align-items: center; 
    }
    .title, .textBody {
        text-align: center;
        padding: 0 10px; 
    }
    .moreButton {
        margin-top: 10px; 
    }
    .AdditionalNewsImage {
        width: 100%; 
        height: auto; 
        max-width: 422px; 
        margin-top: 20px; 
    }
    .tigerTransparent {
        width: 80px; 
        height: auto;
        margin-top: 20px; 
    }
}
</style>