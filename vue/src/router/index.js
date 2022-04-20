import { createRouter, createWebHistory } from 'vue-router'
import Layout from "@/layout/layout";

const routes = [
    // 主页面路由
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: "/user",
    // 嵌套路由
    children: [
      {
        path: 'user',
        name: 'User',
        component: () => import("@/views/User"),
      },
      {
        path: '/order',
        name: 'Order',
        component: () => import("@/views/Order")
      },
      {
        path: '/person',
        name: 'Person',
        component: () => import("@/views/Person")
      },
    ]
  },
    // 登陆界面路由
  {
    path: '/login',
    name: 'Login',
    component: () => import("@/views/Login")
  },
    // 注册页面路由
  {
    path: '/register',
    name: 'Register',
    component: () => import("@/views/Register")
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// // 在刷新页面的时候重置当前路由
// activeRouter()
//
// function activeRouter() {
//   const userStr = sessionStorage.getItem("user")
//   if (userStr) {
//     const user = JSON.parse(userStr)
//     let root = {
//       path: '/',
//       name: 'Layout',
//       component: Layout,
//       redirect: "/home",
//       children: []
//     }
//     user.permissions.forEach(p => {
//       let obj = {
//         path: p.path,
//         name: p.name,
//         component: () => import("@/views/" + p.name)
//       };
//       root.children.push(obj)
//     })
//     if (router) {
//       router.addRoute(root)
//     }
//   }
// }
//
// router.beforeEach((to, from, next) => {
//   if (to.path === '/login' || to.path === '/register') {
//     next()
//     return
//   }
//   let user = sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {}
//   if (!user.permissions || !user.permissions.length) {
//     next('/login')
//   } else if (!user.permissions.find(p => p.path === to.path)) {
//     next('/login')
//   } else {
//     next()
//   }
// })

export default router
