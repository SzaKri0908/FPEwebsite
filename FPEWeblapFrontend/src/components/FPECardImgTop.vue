<template>
    <div class="box">
        <b-card style="margin-bottom: 24px;">
            <div class="image-wrapper">
                <img :src="imgSrc" :alt="title" class="card-image">
                <div class="overlay"></div>
            </div>
            <div class="paragraph-card">
                <b-card-title class="card-title">
                    {{ title }}
                </b-card-title>
                <b-card-text class="card-text">
                    {{ bodyText }}
                </b-card-text>
                <b-link :href="facebookLink" target="_blank">
                    <button class="moreButton">Tovább</button>
                </b-link>
            </div>       
        </b-card>
    </div>
</template>


<script>
    export default {
        name: 'FPECardImgTop',
        props: {
            title: {
                type: String,
                required: true,
            },
            imgSrc: {
                type: String,
                required: true,
            },
            bodyText: {
                type: String,
                required: true,
            },
            facebookLink: {
                type: String,
                required: true,
            },
        },
    }

</script>

<style scoped>
    .box{  
        border-radius: 24px;
        transition: box-shadow 0.3s ease;
        margin-bottom: 16px;
        position: relative
    }
    .box:hover {
        box-shadow: 4px 4px 8px 0 rgba(0, 0, 0, 0.1),
                    -4px 4px 8px 0 rgba(0, 0, 0, 0.1), 
                    0 4px 8px 0 rgba(0, 0, 0, 0.1);
    }
    .card{
        display: flex;
        padding-bottom: 0px;
        flex-direction: column;
        justify-content: center;
        align-items: flex-start;
        gap: 24px;
        align-self: stretch;
        border-radius: 24px;
        background: #FFF;
        border: transparent;
        
    }
    .card-body{
        display: flex;
        padding: 0px;
        flex-direction: column;
        justify-content: center;
        align-items: flex-start;
        gap: 16px;
        align-self: stretch;
        border-radius: 2px;
    }
    .card-text{
        align-self: stretch;
        color: var(--colors-greyscale-dark, #464646);
        font-family: Elza;
        font-size: 18px;
        font-style: normal;
        font-weight: 400;
        line-height: 150%;
        letter-spacing: 0.18px;
    }
    .card-text{
        padding: 0px 16px;
    }
    .paragraph-card a {
        text-decoration: none;
    }
    .card-title{
        align-self: stretch;
        color: var(--Black, #0F202E);
        font-family: Poppins;
        font-size: 24px;
        font-style: normal;
        font-weight: 600;
        line-height: 130%;
        letter-spacing: 0.24px;
        padding: 0px 16px;
    }
    .image-wrapper {
        position: relative;
        overflow: hidden;
        width: 100%;
        border-top-left-radius: 24px;  
        border-top-right-radius: 24px;
        border-bottom-left-radius: 0;
        border-bottom-right-radius: 0; 
    }

    .box img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        object-position: center;
        transition: transform 0.3s ease-in-out;
    }

    .box:hover img {
        transform: scale(1.1);
    }

    .overlay {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.3);
        opacity: 0;
        transition: opacity 0.3s ease-in-out;
    }

    .box:hover .overlay {
        opacity: 1;
    }
    .moreButton{
        margin-bottom: 15px;
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
        margin-left: 15px;
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
</style>