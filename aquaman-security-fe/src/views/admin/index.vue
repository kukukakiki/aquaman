<template>
  <div class="app-container">
    <el-form :inline="true" :model="query" class="demo-form-inline">
      <el-form-item>
        <el-button-group>
          <el-button type="primary" @click="fetchData">查询</el-button>
          <el-button v-show_button="'adminAdd'" type="primary" @click.stop="addHandler">新增</el-button>
          <el-button v-show_button="'adminUpdate'" :disabled="!showButton" type="primary" @click.stop="updateHandler">修改</el-button>
          <el-button v-show_button="'setRole'" :disabled="!showButton" type="primary" @click.stop="toSetRoles">角色</el-button>
        </el-button-group>
      </el-form-item>
      <el-form-item style="float:right">
        <el-button-group>
          <el-button type="primary" icon="el-icon-search" @click="fetchData" />
          <el-button type="primary" icon="el-icon-more" />
          <el-button type="primary" icon="el-icon-refresh" />
        </el-button-group>
      </el-form-item>
      <el-form-item style="float:right">
        <el-input v-model="query.account" placeholder="请输入内容" class="input-with-select">
          <el-select slot="prepend" v-model="query.account" placeholder="请选择">
            <el-option label="用户名" value="1" />
            <el-option label="订单号" value="2" />
            <el-option label="用户电话" value="3" />
          </el-select>
        </el-input>
      </el-form-item>
    </el-form>
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
    <pagination :total="query.total" :page.sync="query.current" :limit.sync="query.size" @pagination="fetchData" />
  </div>
</template>

<script>
import { queryByPage } from '@/api/common'
import Pagination from '@/components/Pagination'

export default {
  components: {
    Pagination
  },
  data() {
    return {
      showButton: false, // 显示执行按钮
      loading: false, // 页面loading标示
      query: { // 列表查询对象
        total: 0, // 总条数
        size: 5, // 每页条数
        current: 1, // 当前页码数
        account: '', // 用户名
        status: '' // 用户状态
      },
      items: [] // 列表集合
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
      queryByPage('user', this.query).then(response => {
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
      if (val) {
        this.showButton = true
        this.selectId = val.id
      } else {
        this.showButton = false
      }
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
    },
    addHandler() {
      this.$router.push({ path: '/systemMessage/adminAdd' })
    },
    updateHandler() {
      this.$router.push({ path: '/systemMessage/adminUpdate' })
    }
  }
}
</script>

<style scoped>
</style>

