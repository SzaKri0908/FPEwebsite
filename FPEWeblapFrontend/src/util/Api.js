import axios from "axios";

export default class Api {
    static getNews(params) {
        const url = process.env.VUE_APP_BACKEND_URL + '/public/listNews';
        return axios.get(url, params)
    }
    static saveNews(params) {
        if(params.id){
            const url = process.env.VUE_APP_BACKEND_URL + '/newsPage/update';
            return axios.put(url, params)
        } else {
            const url = process.env.VUE_APP_BACKEND_URL + '/newsPage/insert';
            return axios.post(url, params)
        }
    }
    static saveEvents(params) {
        if(params.id){
            const url = process.env.VUE_APP_BACKEND_URL + '/events/update';
            return axios.put(url, params)
        } else {
            const url = process.env.VUE_APP_BACKEND_URL + '/events/insert';
            return axios.post(url, params)
        }
    }
    static deleteNews(id) {
        const url = process.env.VUE_APP_BACKEND_URL + '/newsPage/delete?id=' + id;
        return axios.delete(url)
    }
    static getEvents(params) {
        const url = process.env.VUE_APP_BACKEND_URL + '/public/listEvents';
        return axios.get(url, params)
    }
    static sendEmail(params) {
        const url = process.env.VUE_APP_BACKEND_URL + '/public/sendEmail';
        return axios.post(url, params)
    }
    static login(credentials) {
        const url = process.env.VUE_APP_BACKEND_URL+ '/login';
        return axios.post(url, credentials);
    }
    static setAuthToken(token) {
        if (token) {
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        } else {
            delete axios.defaults.headers.common['Authorization'];
        }
    }
    static getGallery(params) {
        const url = process.env.VUE_APP_BACKEND_URL + '/public/listGallery';
        return axios.get(url, params)
    }
}