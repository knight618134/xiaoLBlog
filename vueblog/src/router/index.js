import Vue from 'vue';
import VueRouter from 'vue-router';

import Login from '@/components/Login.vue';
import Home from '@/views/Home.vue';

import WriteBlog from '@/views/blogs/WriteBlog';
import EditBlog from '@/views/blogs/EditBlog';
import AllBlogs from '@/views/blogs/AllBlogs';
import CategoryBlog from '@/views/blogs/CategoryBlog';
import TagBlog from '@/views/blogs/TagBlog';
import CommentBlog from '@/views/blogs/CommentBlog';
import LinksBlog from '@/views/blogs/LinksBlog';
import SysConfig from '@/views/system/SysConfig';
import Area from '@/views/system/Area';
import Menu from '@/views/system/Menu';
import Department from '@/views/permission/Department';
import Organize from '@/views/permission/Organize';
import Order from '@/views/extend/Order';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    redirect: '/admin',
    name: 'Login',
    component: Login,
    hidden: true,
  },

  { path: '/admin', name: 'Login', component: Login, hidden: true },

  {
    path: '/admin/home',
    name: '博客管理',
    component: Home,

    children: [
      {
        path: '/admin/writeblog',
        name: '写博客',
        components: {
          viewAdmin: WriteBlog,
        },
      },
      {
        path: '/admin/editblog/:id',
        name: '编辑博客',
        components: {
          viewAdmin: EditBlog,
        },
        props: true,
        hidden: true,
      },
      {
        path: '/admin/allblogs',
        name: '文章管理',
        components: {
          viewAdmin: AllBlogs,
        },
      },
      {
        path: '/admin/categoryblog',
        components: {
          viewAdmin: CategoryBlog,
        },
        name: '分类专栏',
      },
      {
        path: '/admin/tagblog',
        name: '标签管理',
        components: {
          viewAdmin: TagBlog,
        },
      },
      {
        path: '/admin/commentblog',
        name: '评论管理',
        components: {
          viewAdmin: CommentBlog,
        },
      },
      {
        path: '/admin/linksblog',
        name: '友链管理',
        components: {
          viewAdmin: LinksBlog,
        },
      },
    ],
  },
  {
    path: '/system',
    name: '配置',
    component: Home,
    children: [
      {
        path: '/system/sysConfig',
        name: '系統配置',
        components: {
          viewSystem: SysConfig,
        },
      },
      {
        path: '/system/area',
        name: '行政區別',
        components: {
          viewSystem: Area,
        },
      },
      {
        path: '/system/menu',
        name: '系統功能',
        components: {
          viewSystem: Menu,
        },
      },
    ],
  },
  {
    path: '/permission',
    name: '系統權限',
    component: Home,
    children: [
      {
        path: '/permission/organize',
        name: '組織管理',
        components: {
          viewPermission: Organize,
        },
      },
      {
        path: '/permission/department',
        name: '',
        components: {
          viewPermission: Department,
        },
      },
    ],
  },
  {
    path: '/extend',
    name: '訂單',
    component: Home,
    children: [
      {
        path: '/extend/order',
        name: '訂單管理',
        icon: 'el-icon-s-order',
        components: {
          viewOrder: Order,
        },
      },
    ],
  },
];

const router = new VueRouter({
  routes,
});

export default router;
