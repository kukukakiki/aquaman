<template>
  <div class="app-container">
    <el-form :inline="true" :model="query" class="demo-form-inline">
      <el-form-item label="角色编码">
        <el-input v-model="query.code" placeholder="用户名" />
      </el-form-item>
      <el-form-item label="角色状态">
        <el-select v-model="query.status" placeholder="选择状态">
          <el-option label="启用" value="START" />
          <el-option label="停用" value="STOP" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="fetchData">查询</el-button>
        <el-button v-show_button="'roleAdd'" type="primary" @click.stop="addHandler">新增</el-button>
        <el-button v-show_button="'roleUpdate'" :disabled="!showButton" type="primary" @click.stop="updateHandler">修改</el-button>
        <!--<el-button v-show_button="'roleAuthorization'" :disabled="!showButton" type="primary" @click.stop="authoHandler">授权</el-button>-->
        <el-button :disabled="!showButton" type="primary" @click.stop="deleteHandler">删除</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="items" border style="width: 100%" highlight-current-row @current-change="handleCurrentChange">
      <el-table-column prop="code" label="编码" />
      <el-table-column prop="name" label="名称" />
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
      items: [], // 列表集合
      selectId: '', // 选中ID
      query: { // 列表查询对象
        total: 0, // 总条数
        size: 5, // 每页条数
        current: 1, // 当前页码数
        account: '', // 用户名
        status: '' // 用户状态
      }
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
      queryByPage('role', this.query).then(response => {
        const data = response.result
        if (response.code === '0000') {
          this.items = data.records
          this.query.total = data.total
          this.query.size = data.size
        }
      }).catch(error => {
        this.$message({
          message: error.msg,
          type: 'error',
          duration: 5 * 1000
        })
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
      this.$router.push({ path: '/systemMessage/roleAdd' })
    },
    updateHandler() {
      this.$router.push({
        path: '/systemMessage/roleUpdate',
        query: { id: this.selectId }
      })
    }
  }
}
</script>

<style scoped>
</style>

