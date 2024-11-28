import mutations from './adminPageEvents.mutations'
import actions from './adminPageEvents.actions'
import getters from './adminPageEvents.getters'

import initialState from './defaultState'

const state = initialState();

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
}
