<template>
  <div class="snapshot-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>电子书快照统计</span>
          <el-button type="primary" @click="handleGenSnapshot">生成今日快照</el-button>
        </div>
      </template>

      <!-- 统计图表 -->
      <el-tabs v-model="activeTab" @tab-click="handleTabClick">
        <el-tab-pane label="30天趋势图" name="trend">
          <div ref="trendChartRef" class="chart-container"></div>
        </el-tab-pane>
        <el-tab-pane label="电子书排行榜" name="rank">
          <div ref="rankChartRef" class="chart-container"></div>
        </el-tab-pane>
      </el-tabs>
    </el-card>

    <!-- 快照数据表格 -->
    <el-card class="box-card mt-20">
      <template #header>
        <div class="card-header">
          <span>电子书排行榜</span>
        </div>
      </template>

      <el-table :data="tableData" style="width: 100%" v-loading="loading">
        <el-table-column prop="name" label="电子书名称" />
        <el-table-column prop="viewCount" label="阅读数" sortable />
        <el-table-column prop="voteCount" label="点赞数" sortable />
        <el-table-column prop="viewIncrease" label="阅读增长" sortable />
        <el-table-column prop="voteIncrease" label="点赞增长" sortable />
      </el-table>
    </el-card>
  </div>
</template>

<script lang="ts">
import { genSnapshot, getStatistic, getStatistic30 } from '@/api/ebookSnapshot';
import { ElMessage } from 'element-plus';
import { defineComponent, nextTick, onMounted, onUnmounted, ref } from 'vue';

export default defineComponent({
  name: 'EbookSnapshot',
  setup() {
    // 数据定义
    const loading = ref(false)
    const activeTab = ref('trend')
    const trendChartRef = ref<HTMLElement | null>(null)
    const rankChartRef = ref<HTMLElement | null>(null)
    const trendChart = ref<any | null>(null)
    const rankChart = ref<any | null>(null)
    const tableData = ref<any[]>([])

    // 生成今日快照
    const handleGenSnapshot = async () => {
      try {
        loading.value = true
        const res = await genSnapshot()
        if (res?.success) {
          ElMessage.success('快照生成成功')
          loadData()
        } else {
          ElMessage.error(res?.message || '快照生成失败')
        }
      } catch (error) {
        ElMessage.error('生成快照失败: ' + (error instanceof Error ? error.message : String(error)))
      } finally {
        loading.value = false
      }
    }

    // 初始化空图表
    const initEmptyChart = (chartRef: typeof trendChartRef, chartInstance: typeof trendChart, title: string) => {
      if (!chartRef.value || !chartRef.value.offsetParent) return

      if (!chartInstance.value) {
        chartInstance.value = window.echarts.init(chartRef.value)
      }

      chartInstance.value.setOption({
        title: {
          text: title,
          subtext: '暂无数据'
        },
        xAxis: { type: 'category', data: [] },
        yAxis: { type: 'value' },
        series: []
      })
    }

    // 加载统计数据
    const loadData = async () => {
      try {
        loading.value = true
        await Promise.all([loadTrendData(), loadRankData()])
      } catch (error) {
        ElMessage.error('加载数据失败: ' + (error instanceof Error ? error.message : String(error)))
      } finally {
        loading.value = false
      }
    }

    // 加载趋势图数据
    const loadTrendData = async () => {
      try {
        const res = await getStatistic()

        if (res?.success && res.data) {
          const statisticData = res.data
          const xAxis = statisticData.map((item: any) => item.date)
          const viewCountData = statisticData.map((item: any) => item.viewCount)
          const voteCountData = statisticData.map((item: any) => item.voteCount)
          const viewIncreaseData = statisticData.map((item: any) => item.viewIncrease)
          const voteIncreaseData = statisticData.map((item: any) => item.voteIncrease)

          await nextTick()

          if (!trendChartRef.value || !trendChartRef.value.offsetParent) {
            return
          }

          if (!trendChart.value) {
            trendChart.value = window.echarts.init(trendChartRef.value)
          }

          trendChart.value.setOption({
            title: { text: '电子书阅读趋势' },
            tooltip: { trigger: 'axis' },
            legend: { data: ['总阅读量', '总点赞量', '阅读增长', '点赞增长'] },
            grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
            xAxis: { type: 'category', boundaryGap: false, data: xAxis },
            yAxis: { type: 'value' },
            series: [
              { name: '总阅读量', type: 'line', data: viewCountData },
              { name: '总点赞量', type: 'line', data: voteCountData },
              { name: '阅读增长', type: 'line', data: viewIncreaseData },
              { name: '点赞增长', type: 'line', data: voteIncreaseData }
            ]
          })
        } else {
          initEmptyChart(trendChartRef, trendChart, '电子书阅读趋势')
        }
      } catch (error) {
        initEmptyChart(trendChartRef, trendChart, '电子书阅读趋势')
      }
    }

    // 加载排行榜数据
    const loadRankData = async () => {
      try {
        const res = await getStatistic30()

        if (res?.success && res.data) {
          tableData.value = res.data
          const names = res.data.map((item: any) => item.name)
          const viewCounts = res.data.map((item: any) => item.viewCount)
          const voteCounts = res.data.map((item: any) => item.voteCount)

          await nextTick()

          if (!rankChartRef.value || !rankChartRef.value.offsetParent) {
            return
          }

          if (!rankChart.value) {
            rankChart.value = window.echarts.init(rankChartRef.value)
          }

          rankChart.value.setOption({
            title: { text: '电子书阅读排行' },
            tooltip: { trigger: 'axis', axisPointer: { type: 'shadow' } },
            legend: { data: ['阅读量', '点赞量'] },
            grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
            xAxis: { type: 'value' },
            yAxis: { type: 'category', data: names },
            series: [
              { name: '阅读量', type: 'bar', data: viewCounts },
              { name: '点赞量', type: 'bar', data: voteCounts }
            ]
          })
        } else {
          tableData.value = []
          initEmptyChart(rankChartRef, rankChart, '电子书阅读排行')
        }
      } catch (error) {
        tableData.value = []
        initEmptyChart(rankChartRef, rankChart, '电子书阅读排行')
      }
    }

    // 修改后的处理函数
    const handleTabClick = () => {
      nextTick(() => {
        if (activeTab.value === 'trend') {
          if (trendChart.value && !trendChart.value.isDisposed()) {
            try {
              trendChart.value.resize()
            } catch (e) {
              console.warn('趋势图resize失败:', e)
            }
          }
        } else if (activeTab.value === 'rank') {
          if (rankChart.value && !rankChart.value.isDisposed()) {
            try {
              rankChart.value.resize()
            } catch (e) {
              console.warn('排行榜resize失败:', e)
            }
          }
        }
      })
    }

    const handleResize = () => {
      [trendChart.value, rankChart.value].forEach(chart => {
        if (chart && !chart.isDisposed()) {
          try {
            chart.resize()
          } catch (e) {
            console.warn('图表resize失败:', e)
          }
        }
      })
    }

    // 组件挂载后初始化
    onMounted(() => {
      loadData()
      window.addEventListener('resize', handleResize)
    })

    // 组件卸载时清理
    onUnmounted(() => {
      trendChart.value?.dispose()
      rankChart.value?.dispose()
      window.removeEventListener('resize', handleResize)
    })

    return {
      loading,
      activeTab,
      trendChartRef,
      rankChartRef,
      tableData,
      handleGenSnapshot,
      handleTabClick
    }
  }
})
</script>

<style scoped>
.snapshot-container {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.chart-container {
  width: 100%;
  height: 400px;
}

.mt-20 {
  margin-top: 20px;
}
</style>