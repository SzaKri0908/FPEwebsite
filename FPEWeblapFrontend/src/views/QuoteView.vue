<template>
    <div>
        <b-container fluid class="QuoteViewContainer">
            <b-row>
                <b-col>
                    <img src="../assets/QuoteBlue.svg" alt="Quote" class="Quote">
                </b-col>
            </b-row>
            <div class="textWraper">
                <b-row>
                    <b-col>
                        <p class="heading1"> 
                            Fiatalok Pécsért 
                        </p>
                        <p class="heading2">
                            A jövőnkért, a városunkért! 
                        </p>
                    </b-col>
                </b-row>
                <b-row>
                    <b-col>
                        <p class="footer">
                            Fiatalok Pécsért Egyesület mottója
                        </p>
                    </b-col>
                </b-row>
                <b-row>
                    <b-col>
                        <router-link to="/contact">
                            <button class="moreButton">Tovább</button>
                        </router-link>
                    </b-col>
                </b-row>
            </div>
        </b-container>
    </div>
</template>

<script>
export default {
    name: 'QuoteView',
}
</script>

<style scoped>
    .QuoteViewContainer{
        display: flex;
        width: 1440px;
        padding: 50px 200px 100px 200px;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        gap: 32px;
    }
    .textWraper{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        gap: 24px;
    }
    .textWraper a{
        text-decoration: none;
    }
    .Quote {
      width: 62px;
      height: 50px;
    }
    .heading1{
        color: var(--Blue-Primary, #6CC8F9);
        text-align: center;
        font-family: Elza;
        font-size: 46px;
        font-style: normal;
        font-weight: 600;
        line-height: 150%;
        letter-spacing: -0.69px;
    }

    .heading2{
        color: var(--Black, #0F202E);
        font-family: Elza;
        font-size: 46px;
        font-style: normal;
        font-weight: 600;
        line-height: 150%;
        letter-spacing: -0.69px;
    }
    .footer{
        color: var(--gray-500, #282828);
        text-align: center;
        font-family: Elza;
        font-size: 18px;
        font-style: normal;
        font-weight: 400;
        line-height: 150%;
        letter-spacing: 0.18px;
    }
    .moreButton{
        margin-bottom: 20px;
        display: flex;
        height: 36px;
        padding: 8px 32px;
        justify-content: center;
        align-items: center;
        /* gap: 4px; */
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
        display: flex;
        height: 36px;
        padding: 8px 32px;
        justify-content: center;
        align-items: center;
        /* gap: 4px; */
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
</style>
