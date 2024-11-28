<template>
  <div class="bannerCard">
    <router-link :to="router">
      <b-card :img-src="imgSrc" img-top  :title="title"></b-card>
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'BannerCard',
  props: {
    imgSrc: {
      type: String,
      required: true,
    },
    title: {
      type: String,
      required: true,
    },
    router: {
      type: String,
      required: true,
    },
  },
 
}
</script>

<style scoped>
    .card{
        display: flex;
        width: 242px;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        gap: 24px;
        flex-shrink: 0;
        border:none;
        background: var(--Light-Grey, #F5F5F5);
    }
    .card-img-top{
        height: 189px;
        align-self: stretch;
        border-radius: 24px;
        background: lightgray -15.656px 0px / 125.495% 100%;
    }
    .card-title{
        color: var(--Black, #0F202E);
        text-align: center;
        font-family: Poppins;
        font-size: 24px;
        font-style: normal;
        font-weight: 600;
        line-height: 130%;
        letter-spacing: 0.24px;
    } 
    .card-title:hover{
        color: var(--Yellow-Primary, #F4BC43);
        text-align: center;
        font-family: Poppins;
        font-size: 24px;
        font-style: normal;
        font-weight: 600;
        line-height: 130%;
        letter-spacing: 0.24px;
        cursor: pointer;
    } 
    .bannerCard a {
        text-decoration: none;
    }
    .card img {
      transition: transform 0.3s ease-in-out;
    }
    .card:hover img {
      transform: scale(1.1);
    }
</style>
