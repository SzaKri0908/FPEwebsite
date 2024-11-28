export const deviceMixin = {
    data() {
        return {
            isMobile: false
        };
    },
    created() {
        this.isMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);
        // this.isMobile = true;
    }
};
