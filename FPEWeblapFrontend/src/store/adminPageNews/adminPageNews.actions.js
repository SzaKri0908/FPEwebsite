export default {
    resetState({commit}) {
        commit('resetStateMutation');
    },
    openForm({commit}) {
        commit('setFormOpen');
    },
    closeForm({commit}) {
        commit('setFormClose');
    }
}
