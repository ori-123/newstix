import { createRouter, createWebHistory } from 'vue-router';
import { routes } from './routes';

const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach((to, _, next) => {
    const isAuthenticated = !!localStorage.getItem('token');
    console.log(`Navigating to ${to.path}, requiresAuth: ${to.meta.requiresAuth}, isAuthenticated: ${isAuthenticated}`);
    if (to.meta.requiresAuth && !isAuthenticated) {
        next('/login');
    } else {
        next();
    }
});

export default router;
