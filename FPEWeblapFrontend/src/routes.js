import IndexVue from './views/index.vue';
import HomePageView from './views/HomePageView.vue'
import FreshNewsPageView from './views/FreshNewsPageView.vue'
import ProgramCalendarPageView from './views/ProgramCalendarPageView.vue'
import GalleryPageView from './views/GalleryPageView.vue'
import ContactPageView from './views/ContactPageView.vue'
import LoginView from './views/LoginView.vue'
import AdminGalleryManager from './views/AdminGalleryManager.vue';
import TestInfiniteScroll from './views/TestInfiniteScroll.vue';
import TermsAndConditions from './views/TermsAndConditions.vue';


export default [
    {
        path:'/',
        component: IndexVue,
        redirect: "/home",
        children: [
            {
                path: '/home',
                name: 'Home',
                component: HomePageView
            },
            {
                path: '/news',
                name: 'News',
                component: FreshNewsPageView
            },
            {
                path: '/programs',
                name: 'Programs',
                component: ProgramCalendarPageView
            },
            {
                path: '/gallery',
                name: 'Gallery',
                component: GalleryPageView
            },
            {
                path: '/contact',
                name: 'Contact',
                component: ContactPageView
            },
            {
                path: '/login',
                name: 'LoginView',
                component: LoginView,
                meta: { hideHeaderFooter: true }
            },
            {
                path: '/gallery-manager',
                name: 'AdminGalleryManager',
                component: AdminGalleryManager,
                meta: { hideHeaderFooter: true, requiresAuth: true }
                //meta: { requiresAuth: true }
            },
            {
                path: '/test',
                name: 'Test',
                component: TestInfiniteScroll
            },
            {
                path: '/terms-and-conditions',
                name: 'TermsAndConditions',
                component: TermsAndConditions
            }
        ]
    },
    
]