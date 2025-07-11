import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/Register.vue')
  },
  {
    path: '/main',
    name: 'Main',
    component: () => import('@/views/Main.vue'),
    redirect: '/main/home',
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import('@/views/Home.vue')
      },
      {
        path: 'user',
        name: 'User',
        component: () => import('@/views/user/User.vue')
      },
      {
        path: 'category',
        name: 'Category',
        component: () => import('@/views/category/Category.vue')
      },
      {
        path: 'ebook',
        name: 'Ebook',
        component: () => import('@/views/ebook/Ebook.vue')
      },
      {
        path: 'doc/:ebookId',
        name: 'Doc',
        component: () => import('@/views/doc/Doc.vue')
      },
      {
        path: 'snapshot',
        name: 'Snapshot',
        component: () => import('@/views/snapshot/Snapshot.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  
  // 不需要登录的路由
  if (to.path === '/login' || to.path === '/register') {
    next()
    return
  }
  
  // 需要登录但没有token
  if (!token) {
    next('/login')
    return
  }
  
  // 有token，正常访问
  next()
})

export default router 