import App from "./App.vue";
import Dashboard from "./components/Dashboard.vue";
export const routes = [
    {
        path: '/',
        name: 'Main',
        component: 'App'
    },
    {
        path: '/login',
        name: 'Login',
        component: 'App'
    },
    {
        path: '/register',
        name: 'Register',
        component: 'App'
    },
    {
        path: '/dashboard',
        name: 'Dashboard',
        component: 'Dashboard',
        meta: {
            requireAuth: true
        }
    }
];
