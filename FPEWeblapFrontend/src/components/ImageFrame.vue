<template>
  <div class="frame">
    <img :src="imgSrc" alt="" class="imgClass">
    <div class="overlay"></div>
  </div>
</template>

<script>
export default {
  name: 'ImageFrame',
  props: {
    imgSrc: {
        type: String,
        required: true,
    },
},
}
</script>

<style scoped>
    .frame {
        margin-top: 20px;
        display: flex;
        width: 320px;
        height: 320px;
        justify-content: center;
        align-items: center;
        border-radius: 24px;
        background: lightgray;
        overflow: hidden;
        position: relative;
    }
    .imgClass {
        width: 100%;
        height: 100%;
        object-fit: cover;
        object-position: center;
        transition: transform 0.3s ease-in-out;
    }
    .frame:hover .imgClass {
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
        border-radius: 24px;
    }
    .frame:hover .overlay {
        opacity: 1; 
    }
</style>



