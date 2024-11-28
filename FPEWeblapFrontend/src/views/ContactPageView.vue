<template>
    <div>
        <b-container :class="{'ContactPageViewContainer': true, 'mobile-view': isMobile}">
            <div :class="{'contactWraper': true, 'mobile-contact': isMobile}">
                <p class="contactTitle">Kapcsolat</p>
                <div class="contactInfoWraper">
                    <p class="contactInfoTitle">Elérhetőségeink</p>
                    <div class="contactInfoDetailsWraper">
                        <p class="contactInfo">
                            <svg class="icon" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none">
                                <path d="M22.1721 1.83047C22.0187 1.67713 21.8243 1.5714 21.6122 1.52599C21.4002 1.48058 21.1795 1.49744 20.9768 1.57453L2.22539 8.67187H2.22164C2.00543 8.75503 1.82017 8.9029 1.69114 9.09529C1.56212 9.28768 1.49564 9.5152 1.50076 9.74679C1.50589 9.97838 1.58239 10.2027 1.7198 10.3892C1.85721 10.5757 2.04884 10.7152 2.26851 10.7887L2.28773 10.7948L8.72367 13.5431C8.8492 13.5812 8.98253 13.5857 9.11035 13.5562C9.23817 13.5267 9.35601 13.4641 9.45211 13.3748L19.7815 3.75C19.8123 3.71922 19.8488 3.69481 19.889 3.67815C19.9292 3.66149 19.9723 3.65292 20.0159 3.65292C20.0594 3.65292 20.1025 3.66149 20.1427 3.67815C20.1829 3.69481 20.2195 3.71922 20.2502 3.75C20.281 3.78078 20.3054 3.81732 20.3221 3.85753C20.3387 3.89775 20.3473 3.94085 20.3473 3.98437C20.3473 4.0279 20.3387 4.071 20.3221 4.11122C20.3054 4.15143 20.281 4.18797 20.2502 4.21875L10.6249 14.5434C10.5356 14.6395 10.4731 14.7574 10.4435 14.8852C10.414 15.013 10.4185 15.1463 10.4566 15.2719L13.2059 21.7116C13.2087 21.7209 13.2115 21.7294 13.2148 21.7383C13.3648 22.1728 13.7444 22.4794 14.2034 22.5H14.2502C14.4819 22.5013 14.7086 22.4329 14.9009 22.3037C15.0932 22.1744 15.2421 21.9903 15.3284 21.7753L22.4248 3.02906C22.503 2.82622 22.5207 2.60508 22.4759 2.39236C22.431 2.17965 22.3255 1.98448 22.1721 1.83047Z" fill="#2D2D2D"/>
                            </svg>
                            fiatalokpecsert@outlook.com
                        </p>
                        <p class="contactInfo">
                            <svg class="icon" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none">
                                <path d="M12 1.5C7.85953 1.5 4.5 4.52391 4.5 8.25C4.5 14.25 12 22.5 12 22.5C12 22.5 19.5 14.25 19.5 8.25C19.5 4.52391 16.1405 1.5 12 1.5ZM12 12C11.4067 12 10.8266 11.8241 10.3333 11.4944C9.83994 11.1648 9.45542 10.6962 9.22836 10.1481C9.0013 9.59987 8.94189 8.99667 9.05764 8.41473C9.1734 7.83279 9.45912 7.29824 9.87868 6.87868C10.2982 6.45912 10.8328 6.1734 11.4147 6.05764C11.9967 5.94189 12.5999 6.0013 13.1481 6.22836C13.6962 6.45542 14.1648 6.83994 14.4944 7.33329C14.8241 7.82664 15 8.40666 15 9C14.9991 9.79538 14.6828 10.5579 14.1204 11.1204C13.5579 11.6828 12.7954 11.9991 12 12Z" fill="#2D2D2D"/>
                            </svg>
                            Pécs
                        </p>
                    </div>
                </div>
            </div>
            <div :class="{'contactUsWraper': true, 'mobile-contact-us': isMobile}">
                <p class="contactUsTitle">Írj nekünk!</p>
                <div class="contactUsInputsWraper">
                    <div class="inputWraper">
                        <b-form-input v-model="name" type="text" placeholder="Név" class="contactUsInput"></b-form-input>
                        <b-form-input v-model="email" :state="getEmailState()" @input="validateEmail" type="email" placeholder="Email cím" class="contactUsInput"></b-form-input>
                        <b-form-input v-model="message" type="text" placeholder="Üzenet" class="contactUsInput"></b-form-input>
                    </div>
                    <div class="dataManagementConditions">
                        <label>
                            <input type="checkbox" v-model="checked" unchecked-value="false" value="true" required/>
                        </label>
                        <router-link to="/terms-and-conditions"><span class="dataManagementConditionsText">Az adatkezelési feltételeket elolvastam és elfogadom.</span></router-link>
                    </div>
                </div>
                <div>
                    <button class="sendButton" @click="sendEmail">Küldés</button>
                </div>
            </div>
        </b-container>
        <FPEMottoView v-if="!isMobile"/>
    </div>
</template>
  
  <script>
    import FPEMottoView from './FPEMottoView.vue'
    import { deviceMixin } from '@/deviceMixin';
    import Api from '../util/Api.js'
  
      export default {
        name: 'ContactPageView',
        components: {
            FPEMottoView
        },
        mixins: [deviceMixin],
        data() {
          return {
            name: '',
            email: '',
            message: '',
            checked: false,
          }
        },
        methods: {
            validateEmail() {
                if (this.email.length === 0) {
                    return null;
                }
                const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
                return emailRegex.test(this.email);
            },
            getEmailState() {
                const validationState = this.validateEmail();
                return validationState === null ? null : !!validationState;
            },
            sendEmail() {
                if (this.name === '' || this.email === '' || this.message === '') {
                    let message = 'Kérjük töltse ki az összes mezőt!';
                    this.$toast.error(message, {
                        position: 'top-center',
                        timeout: 3000
                    })
                }else{
                    if(!this.checked){
                        let message = 'Kérjük fogadja el az adatkezelési feltételeket!';
                        this.$toast.error(message, {
                            position: 'top-center',
                            timeout: 3000
                        })
                        return;
                    }
                    let sendEmailData = {
                        name: this.name,
                        email: this.email,
                        message: this.message
                    }
                    Api.sendEmail(sendEmailData).then(() => {
                        let message = 'Az üzenetét sikeresen elküldtük!';
                        this.$toast.success(message, {
                            position: 'top-center',
                            timeout: 3000
                        });
                    }).catch(() => {
                        let message = 'Az üzenet küldése sikertelen!';
                        this.$toast.error(message, {
                            position: 'top-center',
                            timeout: 3000
                        })
                    });
                }
            }
        },

      }
  </script>
  
  <style scoped>
    .ContactPageViewContainer{
        display: flex;
        max-width: 1440px;
        padding: 90px 200px;
        justify-content: space-between;
        align-items: flex-start;
    }
    .contactTitle{
        color: var(--Gray-500, #282828);
        font-family: Elza;
        font-size: 62px;
        font-style: normal;
        font-weight: 700;
        line-height: 130%;
        letter-spacing: 0.62px;
        padding-bottom: 50px;
    }
    .contactWraper{
        display: flex;
        width: 444px;
        flex-direction: column;
        align-items: flex-start;

        flex-shrink: 0;
    }
    .contactInfoWraper{
        display: flex;
        width: 424px;
        flex-direction: column;
        align-items: flex-start;
    
    }
    .contactInfoTitle{
        color: var(--Gray-500, #282828);
        font-family: Poppins;
        font-size: 24px;
        font-style: normal;
        font-weight: 600;
        line-height: 130%; 
        letter-spacing: 0.24px;
        align-self: stretch;
    }
    .contactInfoDetailsWraper{
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        gap: 12px;
        align-self: stretch;
    }
    .contactInfo{
        color: var(--Gray-500, #282828);
        font-family: Elza;
        font-size: 18px;
        font-style: normal;
        font-weight: 400;
        line-height: 150%;
        letter-spacing: 0.18px;
    }
    .icon{
        width: 24px;
        height: 24px;
    }
    .contactUsWraper{
        display: flex;
        width: 442px;
        height: 391px;
        position: relative; 
        padding: 40px 20px 0 20px;
        flex-direction: column;
        justify-content: space-between;
        align-items: flex-start;
        flex-shrink: 0;
        border-radius: 24px;
        background: var(--COLORS-Greyscale-White, #FFF);
        box-shadow: 0px 10px 20px 0px rgba(0, 0, 0, 0.15);
    }
    .contactUsTitle{
        color: var(--Black, #0F202E);
        font-family: Poppins;
        font-size: 24px;
        font-style: normal;
        font-weight: 600;
        line-height: 130%;
        letter-spacing: 0.24px;
    }
    .contactUsInputsWraper{
        margin-bottom: 60px;
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        gap: 12px;
        align-self: stretch;
    }
    .inputWraper{
        padding: 4px;
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        gap: 24px;
        align-self: stretch;
    }
    .contactUsInput{
        height: 29px;
        align-self: stretch;
        display: flex;
        width: 100%;
        padding: 5px;
        flex-direction: column;
        align-items: flex-start;
        gap: 10px;
        border-radius: 2px;
        border-bottom: 1px solid var(--Black, #0F202E);
        border-top: none;
        border-left: none;
        border-right: none;
        color: var(--Grey, #8D8D8D);
        font-family: Elza;
        font-size: 14px;
        font-style: normal;
        font-weight: 400;
        line-height: 150%;
        letter-spacing: 0.14px;
    }
    .dataManagementConditions{
        padding: 4px;
        display: flex;
        align-items: center;
        gap: 8px;
        left: 17%;
    }
    .dataManagementConditionsText{
        color: var(--Grey, #8D8D8D);
        font-family: Elza;
        font-size: 12px;
        font-style: normal;
        font-weight: 500;
        line-height: 150%;
        letter-spacing: 0.12px;
    }
    .sendButton{
        position: absolute; 
        bottom: 40px; 
        left: 80px; 
        transform: translateX(-50%);
        display: flex;
        height: 36px;
        padding: 8px 32px;
        justify-content: center;
        align-items: center;
        gap: 4px;
        flex-shrink: 0;
        border-radius: 50px;
        background: var(--Blue-Primary, #6CC8F9);
        color: var(--Black, #0F202E);
        text-align: center;
        font-family: Circular Std;
        font-size: 16px;
        font-style: normal;
        font-weight: 700;
        line-height: normal;
        border:none;
    }
    .sendButton:hover{
        border-radius: 50px;
        background: var(--Yellow-Primary, #F4BC43);
        box-shadow: 0px 4px 15px 0px rgba(29, 39, 92, 0.20);
    }
@media (max-width: 768px) {
    .ContactPageViewContainer {
        flex-direction: column;
        align-items: center;
        padding: 20px;
    }

    .contactWraper, .contactUsWraper {
        width: 100%; 
        max-width: 100%;
        padding: 20px;
        margin-bottom: 20px;
    }
    .sendButton{
        left: 22%;
    }
    .contactTitle{
        font-size: 46px;
        padding-bottom: 0px;
    }
    p.contactUsTitle{
        margin: 30px 0px 10px 0px !important;
    }
}
.mobile-view {
    flex-direction: column;
    align-items: center;
    padding: 0px 20px 20px 20px;
}

.mobile-contact, .mobile-contact-us {
    width: 100%;
    max-width: 100%;
    padding: 0px 20px 20px 20px;
    margin-bottom: 20px;
}
</style>
  