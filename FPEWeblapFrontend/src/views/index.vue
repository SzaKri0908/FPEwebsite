<template>
    <div class="body-wrapper" >
        <HeaderView v-if="!$route.meta.hideHeaderFooter"/>
        <router-view class="flex-fill overflow-hidden"/>
        <FooterView v-if="!$route.meta.hideHeaderFooter"/>
    </div>
</template>

<script>
    import HeaderView from './HeaderView.vue'
    import FooterView from './FooterView.vue'

    export default {
        name: "IndexVue",
        components: {
            HeaderView,
            FooterView
        },
    }
</script>

<style>
.body-wrapper .content-wrapper {
    overflow-y: auto;
}
</style>
