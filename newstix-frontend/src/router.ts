import { createRouter, createWebHistory } from 'vue-router';
import { routes } from './routes';

const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach((to, _, next) => {
    const isAuthenticated = !!localStorage.getItem('token');
    if (to.meta.requiresAuth && !isAuthenticated) {
        next('/');
    } else {
        next();
    }
});

export default router;
