<template>
    <div>
        <MainPageOpening/>
        <FreshNewsView :isPage="false"/>
        <ProgramCalendarView/>
        <AdditionalNewsView/>
        <BannerView/>
    </div>
</template>

<script>
    import MainPageOpening from './MainPageOpening.vue'
    import FreshNewsView from './FreshNewsView.vue'
    import ProgramCalendarView from './ProgramCalendarView.vue'
    import AdditionalNewsView from './AdditionalNewsView.vue'
    import BannerView from './BannerView.vue'
    

    export default {
        name: 'HomePageView',
        components: {
            MainPageOpening,
            FreshNewsView,
            ProgramCalendarView,
            AdditionalNewsView,
            BannerView,
        },
    }
</script>

<style>
</style>
