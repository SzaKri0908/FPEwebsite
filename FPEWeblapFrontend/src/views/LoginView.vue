<template>
    <div class="body">
      <div class="background">
        <div>
            <img src="../assets/TigerBack.svg" alt="tiger" class="mottoTiger">
        </div>
      </div>
      <b-container class="login-container">
        <b-row class="justify-content-md-center">
          <b-col cols="12" md="6" lg="4">
            <b-card class="p-4 login-card">
              <div class="text-center mb-3">
                <b-avatar variant="light" style="background-color: #5CAAD4;" class="mb-2">
                  <b-icon icon="person" font-scale="2"></b-icon>
                </b-avatar>
                <h2 class="loginText">Bejelentkezés</h2>
              </div>
              <b-form @submit.prevent="submitHandlerBeta" ref="form">
                <b-form-group class="email-input-group">
                  <div class="input-icon-wrapper">
                    <b-icon icon="person" class="input-icon"></b-icon>
                    <b-form-input
                      v-model="username"
                      placeholder="Felhasználónév"
                      class="input-with-icon"
                    ></b-form-input>
                  </div>
                </b-form-group>

                <b-form-group  class="password-input-group">
                  <div class="input-icon-wrapper">
                    <b-icon icon="key" class="input-icon"></b-icon>
                    <b-form-input
                      v-model="password"
                      :type="passwordShow ? 'text' : 'password'"
                      placeholder="Jelszó"
                      aria-describedby="password-live-feedback"
                      class="input-with-icon password-input"
                    ></b-form-input>
                    <b-icon
                      :icon="passwordShow ? 'eye-fill' : 'eye-slash-fill'"
                      class="toggle-password-icon"
                      @click="togglePasswordVisibility"
                    ></b-icon>
                  </div>
                  <b-form-invalid-feedback id="password-live-feedback">
                    {{ passwordFeedback }}
                  </b-form-invalid-feedback>
                </b-form-group>
                <div class="form-check form-switch">
                  <input v-model="rememberMe" class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckDefault" style="transform: scale(1.2)">
                  <label class="form-check-label" for="flexSwitchCheckDefault" style="padding-left: 0.5rem">Emlékezz rám</label>
                </div>
                <div class="button-container">
                  <b-button :disabled="loading" type="submit" class="mt-3 mb-3 loginButton">
                    <span v-if="loading" class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                    {{ loading ? ' Betöltés...' : 'Bejelentkezés' }}
                  </b-button>
                </div>
              </b-form>
            </b-card>
          </b-col>
        </b-row>
      </b-container>
    </div>
</template>

<script>
import Api from '../util/Api.js'

export default {
  name: 'LoginView',
  data() {
    return {
      loading: false,
      snackbar: false,
      passwordShow: false,
      email: '',
      username: '',
      password: '',
      rememberMe: false,
    };
  },
  created() {
    const jwtToken = localStorage.getItem('jwt-token') || sessionStorage.getItem('jwt-token');
    if (jwtToken) {
      this.$router.push({ name: 'AdminGalleryManager' });
    }
  },
  methods: {
    //TODO: befejezni a toastot hogy menő legyen
    submitHandlerBeta() {
      this.loading = true;

      setTimeout(() => {
        Api.login({
          username: this.username,
          password: this.password,
        })
        .then(response => {
          const token = response.headers['authorization'] || response.headers['Authorization'];
          if (this.rememberMe) {
            localStorage.setItem('jwt-token', token);
          } else {
            sessionStorage.setItem('jwt-token', token);
          }
          this.showSuccessToast(response.data.reason);
          const redirect = this.$route.query.redirect || { name: 'AdminGalleryManager' }; // default redirect
          setTimeout(() => {
            this.$router.push(redirect);
          }, 1500);
        })
        .catch(error => {
          console.error('Login failed:', error);
          this.showErrorToast(error.response.data.reason)
        }).finally(() => {
          this.loading = false;
        });
      }, 3000);
    },
    showSuccessToast(message) {
      this.$toast.success(message, {
        position: 'top-center',
        timeout: 3000
      })
    },
    showErrorToast(apiMessage){
      const message = `Sikertelen bejelentkezés! \n${apiMessage}`
      this.$toast.error(message, {
        position: 'top-center',
        timeout: 3000
      })
    },
    validateEmail(email) {
      const pattern = /.+@.+\..+/;
      return pattern.test(email);
    },
    validatePassword(password) {
      return password && password.length >= 6;
    },
    validateForm() {
      return this.validateEmail(this.email) && this.validatePassword(this.password);
    },
    togglePasswordVisibility() {
      this.passwordShow = !this.passwordShow;
    },
    navigateToRegistration() {
      this.$router.push({ name: 'Registration' });
    },
  },
  computed: {
    emailState() {
    if (this.email === '') {
        return null;
      } else {
        return this.validateEmail(this.email);
      }
    },
    emailFeedback() {
      if (!this.email) {
        return 'E-mail is required';
      } else if (!this.validateEmail(this.email)) {
        return 'E-mail must be valid';
      }
      return '';
    },
    passwordFeedback() {
      if (!this.password) {
        return 'Password is required';
      } else if (!this.validatePassword(this.password)) {
        return 'Password must be 6 characters or more!';
      }
      return '';
    },
  },
};
</script>

<style scoped>
.body {
  background-color: #6cc8f9;
  min-height: 100vh;
}
.form-check.form-switch {
  padding-left: 1.4rem
}
 .loginText{
    color: #6CC8F9;
  }
  .login-container {
    margin-top: 170px;
  }
  .login-card {
  border: none;
  box-shadow: 0px 5px 10px rgba(29, 39, 92, 0.4);
  margin-bottom: 40px;
  }
  .button-container {
    margin-top: 40px;
    display: flex;
    justify-content: center;
  }
  .background {
    background: var(--Blue-Light, #EDF9FF);
    box-shadow: 0px 8px 15px 0px rgba(29, 39, 92, 0.20);
    height: 350px;
    width: 100%;
    display: block;
    position: absolute;
    top: 0;
    background-size: cover;
    overflow: hidden;
  }
  .email-input-group {
    margin-bottom: 20px; /* Adjust this value to increase or decrease the gap */
  }

  .password-input-group {
    margin-bottom: 20px; /* Adjust this value to increase or decrease the gap */
  }
  .mottoTiger {
    margin-top: 15px;
    width: 497.315px;
    height: 350px; 
    float: right;
  }
 
  .input-icon-wrapper {
    position: relative;
    display: flex;
    align-items: center;
  }

  .input-with-icon {
    flex: 1;
    padding-left: 30px; /* Add padding to ensure text does not overlap the icon */
    padding-right: 30px; /* Add padding to ensure text does not overlap the toggle icon */
  }

  .input-icon, .toggle-password-icon {
    position: absolute;
    z-index: 10;
  }

  .input-icon {
    left: 10px; /* Distance from the left */
  }

  .toggle-password-icon {
    right: 10px; /* Distance from the right */
    cursor: pointer; /* Change cursor to pointer when hovering over the icon */
  }

  .password-input {
    padding-right: 45px; /* Add padding to ensure text does not overlap the toggle icon */
  }
  .loginButton{
    border-radius: 50px;
    background: var(--Blue-Primary, #6CC8F9);
    border: none;
    outline: none;
    color: var(--Black, #0F202E);
    padding: 10px 36px; /* Increased padding for a larger button */
    font-size: 18px; /* Slightly larger font size */
  }

  .loginButton:hover, .loginButton:focus, .loginButton:active {
    background: var(--Yellow-Primary, #F4BC43) !important;
    box-shadow: 0px 4px 15px 0px rgba(29, 39, 92, 0.20);
}
.loginButton:disabled {
    background: var(--Blue-Primary, #6CC8F9);
    color: var(--Black, #0F202E);
    opacity: 0.7;
}
.b-avatar {
    background-color: #0F202E; /* This sets the background color */
    border-radius: 50%; /* This makes the avatar circular */
}

.b-avatar .b-icon {
    color: #fff; /* This sets the icon color, change if needed */
}

.registration-text {
  color: #333; /* Adjust color as needed */
  margin-right: 5px;
}

.registration-link {
  color: var(--Blue-Primary, #6CC8F9); /* Use your theme's primary color */
  cursor: pointer;
  font-weight: bold;
}

.registration-link:hover {
  text-decoration: underline; /* Add underline on hover to indicate it's clickable */
}
</style>

