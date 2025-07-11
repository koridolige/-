<template>
  <div class="main-container">
    <el-container class="main-layout">
      <!-- 侧边栏 -->
      <el-aside :width="isCollapse ? '64px' : '200px'" class="main-aside">
        <div class="logo-container">
          <span v-if="!isCollapse">海洋生物系统</span>
          <el-icon v-else><Menu /></el-icon>
        </div>
        <el-menu :collapse="isCollapse" :default-active="activeMenu" router background-color="#001529" text-color="#fff" active-text-color="#1890ff">
          <el-menu-item index="/main/home">
            <el-icon><HomeFilled /></el-icon>
            <span>首页</span>
          </el-menu-item>
          <el-menu-item index="/main/user">
            <el-icon><User /></el-icon>
            <span>用户管理</span>
          </el-menu-item>
          <el-menu-item index="/main/category">
            <el-icon><Histogram /></el-icon>
            <span>分类管理</span>
          </el-menu-item>
          <el-menu-item index="/main/ebook">
            <el-icon><Reading /></el-icon>
            <span>电子书管理</span>
          </el-menu-item>
          <el-menu-item index="/main/snapshot">
            <el-icon><PieChart /></el-icon>
            <span>统计信息</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-container class="main-content">
        <!-- 头部 -->
        <el-header class="main-header">
          <div class="header-left">
            <el-icon class="collapse-btn" @click="toggleCollapse">
              <Expand v-if="isCollapse" />
              <Fold v-else />
            </el-icon>
          </div>
          <div class="header-right">
            <el-dropdown trigger="click" @command="handleCommand">
              <span class="user-dropdown">
                {{ userStore.userInfo?.name || '管理员' }}
                <el-icon><ArrowDown /></el-icon>
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </el-header>

        <!-- 内容区域 -->
        <el-main class="main-body">
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import { useUserStore } from '@/stores/user';
import { ArrowDown, Expand, Fold, Histogram, HomeFilled, Menu, PieChart, Reading, User } from '@element-plus/icons-vue';
import { computed, ref } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute()
const userStore = useUserStore()
const isCollapse = ref(false)

// 当前激活的菜单
const activeMenu = computed(() => {
  return route.path
})

// 切换侧边栏折叠状态
const toggleCollapse = () => {
  isCollapse.value = !isCollapse.value
}

// 处理下拉菜单命令
const handleCommand = (command: string) => {
  if (command === 'logout') {
    userStore.logout()
  }
}
</script>

<style scoped lang="scss">
.main-container {
  height: 100vh;
  width: 100vw;
}

.main-layout {
  height: 100%;
}

.main-aside {
  background-color: #001529;
  transition: width 0.3s;
  overflow: hidden;

  .logo-container {
    height: 60px;
    line-height: 60px;
    text-align: center;
    color: #fff;
    font-size: 18px;
    font-weight: bold;
    overflow: hidden;
  }
}

.main-content {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.main-header {
  background-color: #fff;
  border-bottom: 1px solid #e8e8e8;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;

  .collapse-btn {
    font-size: 20px;
    cursor: pointer;
  }

  .user-dropdown {
    cursor: pointer;
    display: flex;
    align-items: center;

    .el-icon {
      margin-left: 5px;
    }
  }
}

.main-body {
  background-color: #f0f2f5;
  padding: 20px;
  overflow: auto;
}
</style>