export default class LoginUtil {

    static isLoggedIn() {
        let token = localStorage.getItem('token');
        return !!token;
    }
}
