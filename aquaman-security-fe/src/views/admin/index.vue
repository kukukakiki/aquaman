<template>
  <div class="app-container">
    <el-table v-loading="loading" :data="items" border style="width: 100%" highlight-current-row @current-change="handleCurrentChange">
      <el-table-column prop="account" label="用户名" />
      <el-table-column prop="nickName" label="昵称" />
      <el-table-column prop="email" label="昵称" />
      <el-table-column prop="gmtCreate" label="创建时间">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ formateDate(scope.row.gmtCreate) }}</span>
        </template>
      </el-table-column>
    </el-table>
    <commons-page :total="query.total" :page-size="query.size" />
  </div>
</template>

<script>
import { queryUserByPage } from '@/api/user'
import commonsPage from '@/components/Commons/Page'

export default {
  components: {
    commonsPage
  },
  data() {
    return {
      loading: false,
      /**
       * 列表查询对象
       */
      query: {
        total: 0,
        size: 10,
        current: 1
      },
      /**
       * 列表集合
       */
      items: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    /**
     * 获取数据
     */
    fetchData() {
      this.loading = true
      queryUserByPage(this.query).then(response => {
        const data = response.result
        if (response.code === '0000') {
          this.items = data.records
          this.query.total = data.total
          this.query.size = data.size
        }
        this.loading = false
      }).catch(error => {
        this.$message({
          message: error.msg,
          type: 'error',
          duration: 5 * 1000
        })
        this.loading = false
      })
    },
    /**
     * 获取当前列表选中行
     */
    handleCurrentChange(val) {
      console.log(val)
    },
    /**
     * 列表时间转换
     */
    formateDate(cellValue) {
      if (cellValue !== undefined && cellValue !== null && cellValue !== '') {
        const dateTime = cellValue + ''
        if (dateTime.length === 14) {
          const fullYear = dateTime.substring(0, 4)
          const month = dateTime.substring(4, 6)
          const day = dateTime.substring(6, 8)
          const hours = dateTime.substring(8, 10)
          const minutes = dateTime.substring(10, 12)
          const seconds = dateTime.substring(12, 14)
          return fullYear + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds
        } else {
          return '暂无时间'
        }
      } else {
        return '暂无时间'
      }
    }
  }
}
</script>

<style scoped>
</style>

