<template>
  <div>
    <b-container class="FreshNewsPageViewContainer">
        <b-row>
            <b-col class="pl-0">
                <p class="title">Hírek</p>
            </b-col>
        </b-row>
    </b-container>
    <FreshNewsView :isPage="true"/>
  </div>
</template>

<script>
    import FreshNewsView from './FreshNewsView.vue'
    import { deviceMixin } from '@/deviceMixin';

    export default {
        name: 'FreshNewsPageView',
        components: {
            FreshNewsView,
        },
        mixins: [deviceMixin],
    }
</script>

<style scoped>
.FreshNewsPageViewContainer {
    padding: 0px 70px;
}

.title {
    color: var(--gray-500, #282828);
    font-family: Elza;
    font-size: 46px;
    font-style: normal;
    font-weight: 700;
    line-height: 130%;
    letter-spacing: 0.62px;
}

@media (max-width: 768px) {
    .FreshNewsPageViewContainer {
        padding: 10px 20px;
    }
    
    .title {
        font-size: 35px !important;
    }
}
</style>

