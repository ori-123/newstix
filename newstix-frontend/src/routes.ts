
export const routes = [
    {
        path: '/',
        name: 'Main',
        component: () => import('./App.vue')
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('./components/Login.vue')
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import('./components/Register.vue')
    },
    {
        path: '/dashboard',
        name: 'Dashboard',
        component: () => import('./components/Dashboard.vue'),
        meta: {
            requiresAuth: true
        }
    }
];
