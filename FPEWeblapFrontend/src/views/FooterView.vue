<template>
    <div>
        <div class="FooterContainerWrapper">
            <div class="FooterContainer">
                <router-link to="/home">
                    <b-row>
                        <b-col class="text-center">
                            <img class="tigerTransparent" src="../assets/Rectangle99.svg" alt="tigerTransparent">
                        </b-col>
                    </b-row>
                </router-link>
                <b-row v-if="!isMobile">
                    <b-col>
                        <b-nav align="center" class="navItems">
                            <b-nav-item active>
                                <router-link to="/home">
                                    <b>Fiatalok Pécsért Egyesület</b>
                                </router-link>
                            </b-nav-item>
                            <b-nav-item>
                                <router-link to="/news">
                                    <b>Hírek</b> 
                                </router-link>
                            </b-nav-item>
                            <b-nav-item>
                                <router-link to="/programs">
                                    <b>Programok</b>
                                </router-link>
                            </b-nav-item>
                            <b-nav-item>
                                <router-link to="/gallery">
                                    <b>Galéria</b>
                                </router-link>
                            </b-nav-item>
                            <b-nav-item>
                                <router-link to="/contact">
                                    <b>Kapcsolat</b>
                                </router-link>
                            </b-nav-item>
                        </b-nav>
                    </b-col>
                </b-row>
                <b-row>
                    <b-col>
                        <p class="copyright">
                            ©Fiatalok Pécsért Egyesület
                        </p>
                    </b-col>
                </b-row>
            </div>
        </div>
    </div>
</template>

<script>
    import { deviceMixin } from '@/deviceMixin';

    export default {
        name: 'QuoteView',
        mixins: [deviceMixin],
    }
</script>

<style scoped>
.FooterContainerWrapper {
    background: var(--Black, #0F202E);
}
.FooterContainer {
    display: flex;
    padding: 24px 0; 
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 16px;
    flex-wrap: wrap;
}
.tigerTransparent {
    width: 181px;
    height: 103px; 
    flex-shrink: 0;
}
.navItems a, .navItemsMobile a {
    text-decoration: none;
}
b {
    color: rgba(237, 249, 255, 1);
}
b:hover {
    color: rgba(244, 188, 67, 1);
}
.copyright {
    color: var(--Grey, #8D8D8D);
    text-align: center;
    font-size: 14px;
}
@media (max-width: 768px) {
    .FooterContainer {
        padding: 12px 0;
    }
    .tigerTransparent {
        width: 120px;
        height: auto; 
    }
}
</style>

