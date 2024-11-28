<template>
    <b-link :href="facebookEventLink" target="_blank"  class="cardFrame">
        <b-card style="margin-bottom: 24px; position: relative;">
            <div class="content-wrapper">
                <b-card-title class="card-title">{{ title }}</b-card-title>
                <b-card-text class="card-text">{{ bodyText }}</b-card-text>
            </div>
            <div class="image-wrapper">
                <img :src="imgSrc" :alt="title" class="card-image">
                <div class="overlay"></div>
            </div>
        </b-card>
    </b-link>
</template>

<script>
    export default {
        name: 'FPECardImgBottom',
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
            facebookEventLink: {
                type: String,
                required: true,
            },
        },
    }

</script>

<style scoped>
    .cardFrame {
        text-decoration: none;
    }
    .card {
        display: flex;
        flex-direction: column;
        width: 334px;
        padding-top: 32px;
        border-radius: 24px;
        background: #FFF;
        border: none;
        position: relative;
        transition: box-shadow 0.3s ease;
    }
    .card-body{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: flex-start;
        gap: 16px;
        align-self: stretch;
        border-radius: 2px;
        padding: 0;
    }
    .card:hover {
        box-shadow: 4px 4px 8px 0 rgba(0, 0, 0, 0.1), 
                    -4px 4px 8px 0 rgba(0, 0, 0, 0.1), 
                    0 4px 8px 0 rgba(0, 0, 0, 0.1);
    }

    .image-wrapper {
        border-top-left-radius: 0;  
        border-top-right-radius: 0;
        border-bottom-left-radius: 24px;
        border-bottom-right-radius: 24px; 
        position: relative;
        width: 100%;
        overflow: hidden;
    }

    .card-image {
        width: 100%;
        height: auto;
        transition: transform 0.3s ease-in-out;
    }

    .card:hover .card-image {
        transform: scale(1.1);
    }

    .overlay {
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        border-bottom-left-radius: 24px; 
        border-bottom-right-radius: 24px; 
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.3);
        opacity: 0;
        transition: opacity 0.3s ease-in-out;
    }

    .card:hover .overlay {
        opacity: 1;
    }

    .content-wrapper {
        padding: 0 16px;
    }

    .card-title {
        align-self: stretch;
        color: var(--Black, #0F202E);
        font-family: Poppins;
        font-size: 24px;
        font-style: normal;
        font-weight: 600;
        line-height: 130%;
        letter-spacing: 0.24px;
        padding-right: 2rem;
    }

    .card-text {
        align-self: stretch;
        color: var(--Black, #0F202E);
        font-family: Elza;
        font-size: 18px;
        font-style: normal;
        font-weight: 700;
        line-height: 150%;
        letter-spacing: 0.18px;
    }
</style>
