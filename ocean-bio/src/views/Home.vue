<template>
  <div class="home-container">
    <el-row :gutter="20">
      <!-- 统计卡片 -->
      <el-col :span="6">
        <el-card shadow="hover"
                 class="stat-card">
          <div class="stat-icon">
            <el-icon>
              <User />
            </el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-title">用户数量</div>
            <div class="stat-value">{{ stats.userCount }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover"
                 class="stat-card">
          <div class="stat-icon category-icon">
            <el-icon>
              <Menu />
            </el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-title">分类数量</div>
            <div class="stat-value">{{ stats.categoryCount }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover"
                 class="stat-card">
          <div class="stat-icon ebook-icon">
            <el-icon>
              <Reading />
            </el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-title">电子书数量</div>
            <div class="stat-value">{{ stats.ebookCount }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover"
                 class="stat-card">
          <div class="stat-icon doc-icon">
            <el-icon>
              <Document />
            </el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-title">文档数量</div>
            <div class="stat-value">{{ stats.docCount }}</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 欢迎信息 -->
    <el-card class="welcome-card mt-10">
      <template #header>
        <div class="card-header">
          <span>欢迎使用</span>
        </div>
      </template>
      <div class="welcome-content">
        <h2>海洋生物系统</h2>
        <p>这是一个用于管理海洋生物知识的系统，包含分类管理、电子书管理、文档管理等功能。</p>
        <div class="feature-list">
          <div class="feature-item">
            <el-icon>
              <User />
            </el-icon>
            <span>用户管理</span>
          </div>
          <div class="feature-item">
            <el-icon>
              <Menu />
            </el-icon>
            <span>分类管理</span>
          </div>
          <div class="feature-item">
            <el-icon>
              <Reading />
            </el-icon>
            <span>电子书管理</span>
          </div>
          <div class="feature-item">
            <el-icon>
              <Document />
            </el-icon>
            <span>文档管理</span>
          </div>
        </div>
      </div>
    </el-card>

    <!-- 海洋生物展示 -->
    <el-card class="mt-10">
      <template #header>
        <div class="card-header">
          <span>海洋生物展示</span>
        </div>
      </template>
      <el-row :gutter="30">
        <el-col :span="8"
                v-for="(image, index) in oceanImages"
                :key="index">
          <el-card shadow="hover"
                   class="image-card">
            <img :src="image.url"
                 class="ocean-image" />
            <div class="image-title">{{ image.title }}</div>
          </el-card>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { onMounted, reactive } from 'vue';

// 统计数据
const stats = reactive({
  userCount: 0,
  categoryCount: 0,
  ebookCount: 0,
  docCount: 0,
})

// 海洋生物图片
const oceanImages = reactive([
  { title: '海豚', url: '/images/haitun.jpg' },
  { title: '海藻', url: '/images/haizao.jpg' },
  { title: '水母', url: '/images/shuimu.jpg' },
  { title: '珊瑚', url: '/images/hujing.jpg' },
  { title: '魔鲸鲨', url: '/images/moxiangjing.jpg' },
  { title: '大白鲨', url: '/images/dwwz.jpg' },
])

// 模拟获取统计数据
const fetchStats = () => {
  // 实际开发中应该从API获取
  stats.userCount = 10
  stats.categoryCount = 8
  stats.ebookCount = 25
  stats.docCount = 120
}

onMounted(() => {
  fetchStats()
})
</script>

<style scoped lang="scss">
.home-container {
  padding: 20px;
}

.stat-card {
  display: flex;
  align-items: center;
  height: 150px;

  .stat-icon {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    background-color: #1890ff;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 20px;

    .el-icon {
      font-size: 36px;
      color: #fff;
    }
  }

  .category-icon {
    background-color: #52c41a;
  }

  .ebook-icon {
    background-color: #faad14;
  }

  .doc-icon {
    background-color: #13c2c2;
  }

  .stat-info {
    .stat-title {
      font-size: 16px;
      color: #666;
      margin-bottom: 10px;
    }

    .stat-value {
      font-size: 32px;
      font-weight: bold;
      color: #333;
      margin-top: 5px;
    }
  }
}

.welcome-card {
  margin-top: 20px;

  .welcome-content {
    text-align: center;
    padding: 20px;

    h2 {
      font-size: 28px;
      color: #1890ff;
      margin-bottom: 15px;
    }

    p {
      font-size: 16px;
      color: #666;
      margin-bottom: 30px;
    }

    .feature-list {
      display: flex;
      justify-content: space-around;
      margin-top: 30px;

      .feature-item {
        display: flex;
        flex-direction: column;
        align-items: center;

        .el-icon {
          font-size: 32px;
          color: #1890ff;
          margin-bottom: 10px;
        }

        span {
          font-size: 16px;
        }
      }
    }
  }
}

.image-card {
  margin-bottom: 30px;

  .ocean-image {
    width: 100%;
    height: 500px;
    object-fit: cover;
  }

  .image-title {
    text-align: center;
    padding: 15px 0;
    font-size: 18px;
    font-weight: bold;
  }
}
</style> 