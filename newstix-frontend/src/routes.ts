
export const routes = [
    {
        path: '/',
        name: 'Main',
        component: () => import('./App.vue')
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('./App.vue')
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import('./App.vue')
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
