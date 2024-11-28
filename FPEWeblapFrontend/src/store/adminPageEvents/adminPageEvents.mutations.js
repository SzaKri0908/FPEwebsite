import Vue from 'vue'

export default {
    resetStateMutation(state) {
        Vue.set(state, "gridIsOpen", true);
        Vue.set(state, "formIsOpen", false);
    },
    setFormOpen(state) {
        state.formIsOpen = true;
        state.gridIsOpen = false;
    },
    setFormClose(state) {
        state.formIsOpen = false;
        state.gridIsOpen = true;
    }
}
