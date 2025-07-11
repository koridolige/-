# 海洋生物系统前端

这是海洋生物系统的前端项目，基于 Vue 3 + TypeScript + Vite + Element Plus 开发。

## 技术栈

- Vue 3
- TypeScript
- Vite
- Element Plus
- Vue Router
- Pinia
- Axios

## 项目结构

```
ocean-bio/
├── public/                 # 静态资源
├── src/                    # 源代码
│   ├── api/                # API请求
│   ├── assets/             # 资源文件
│   │   ├── styles/         # 样式文件
│   │   └── images/         # 图片资源
│   ├── components/         # 公共组件
│   ├── router/             # 路由配置
│   ├── stores/             # Pinia状态管理
│   ├── views/              # 页面组件
│   │   ├── category/       # 分类管理
│   │   ├── doc/            # 文档管理
│   │   ├── ebook/          # 电子书管理
│   │   └── user/           # 用户管理
│   ├── App.vue             # 根组件
│   └── main.ts             # 入口文件
├── .gitignore              # Git忽略文件
├── index.html              # HTML模板
├── package.json            # 项目依赖
├── tsconfig.json           # TypeScript配置
├── tsconfig.node.json      # Node.js TypeScript配置
└── vite.config.ts          # Vite配置
```

## 开发环境

- Node.js >= 16.0.0
- npm >= 8.0.0

## 安装依赖

```bash
npm install
```

## 启动开发服务器

```bash
npm run dev
```

## 构建生产版本

```bash
npm run build
```

## 预览生产构建

```bash
npm run preview
```

## 功能模块

1. **用户管理**：用户的增删改查
2. **分类管理**：海洋生物分类的增删改查
3. **电子书管理**：电子书的增删改查
4. **文档管理**：文档的增删改查及内容编辑

## 接口说明

项目通过 Axios 与后端 API 进行交互，所有 API 请求都在`src/api`目录下。

## 项目配置

- 开发环境代理配置在`vite.config.ts`中
- 路由配置在`src/router/index.ts`中
- 状态管理配置在`src/stores`目录下
