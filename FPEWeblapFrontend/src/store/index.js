import Vue from 'vue';
import Vuex from 'vuex';

import adminPageNews from './adminPageNews/index.js';
import adminPageEvents from './adminPageEvents/index.js';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
      adminPageNews,
      adminPageEvents
    }
})
