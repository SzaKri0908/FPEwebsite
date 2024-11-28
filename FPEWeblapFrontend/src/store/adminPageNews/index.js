import mutations from './adminPageNews.mutations'
import actions from './adminPageNews.actions'
import getters from './adminPageNews.getters'

import initialState from './defaultState'

const state = initialState();

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
}
