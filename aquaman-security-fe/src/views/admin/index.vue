<template>
  <div class="app-container">
    <el-form ref="queryForm" :inline="true" :model="query" class="demo-form-inline">
      <el-row>
        <el-col :span="8">
          <el-button-group>
            <el-button v-show_button="'adminAdd'" type="primary" @click.stop="addHandler">新增</el-button>
            <el-button v-show_button="'adminView'" :disabled="!showButton" type="primary" @click="viewHandler">查阅</el-button>
            <el-button v-show_button="'adminUpdate'" :disabled="!showButton" type="primary" @click.stop="updateHandler">修改</el-button>
            <el-button v-show_button="'setRole'" :disabled="!showButton" type="primary" @click.stop="setRolesHandler">角色</el-button>
          </el-button-group>
        </el-col>
        <el-col :span="16">
          <el-button-group style="float:right">
            <el-button type="primary" icon="el-icon-search" title="查询列表" @click="fetchData" />
            <el-button type="primary" icon="el-icon-more" title="更多条件" @click="showMoreQuery" />
            <el-button type="primary" icon="el-icon-refresh" title="清空条件" @click="resetQuery" />
          </el-button-group>
          <el-form-item label="用户名称" style="float:right" prop="account">
            <el-input v-model="query.account" placeholder="用户名称" />
          </el-form-item>
        </el-col>
      </el-row>
      <transition name="slide-fade">
        <div v-if="hiddenQuery">
          <el-row class="my_row">
            <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
              <el-form-item label="用户状态" prop="status">
                <el-select v-model="query.status" placeholder="选择状态">
                  <el-option label="启用" value="START" />
                  <el-option label="停用" value="STOP" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </div>
      </transition>
    </el-form>
    <el-table v-loading="loading" :data="items" border style="width: 100%" highlight-current-row @current-change="handleCurrentChange">
      <el-table-column prop="account" label="用户名" />
      <el-table-column prop="name" label="姓名" />
      <el-table-column prop="nickName" label="昵称" />
      <el-table-column prop="email" label="邮箱" />
      <el-table-column prop="gmtCreate" label="创建时间">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ formateDate(scope.row.gmtCreate) }}</span>
        </template>
      </el-table-column>
    </el-table>
    <pagination :total="query.total" :page.sync="query.current" :limit.sync="query.size" @pagination="fetchData" />

    <el-dialog
      :visible.sync="dialogVisible"
      :title="setRoleUserTitle"
      width="70%">
      <check-box-role />
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUserRolesHandler">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { queryByPage } from '@/api/common'
import { queryRoleByAll } from '@/api/role'
import Pagination from '@/components/Pagination'
import CheckBoxRole from '@/components/Business/Role/CheckBoxRole'

export default {
  components: {
    Pagination,
    CheckBoxRole
  },
  data() {
    return {
      hiddenQuery: false,
      showButton: false, // 显示执行按钮
      loading: false, // 页面loading标示
      setRoleUserTitle: '',
      dialogVisible: false,
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
    showMoreQuery() {
      if (this.hiddenQuery === true) {
        this.hiddenQuery = false
      } else if (this.hiddenQuery === false) {
        this.hiddenQuery = true
      }
    },
    resetQuery() {
      this.$refs['queryForm'].resetFields()
    },
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
        this.setRoleUserTitle = '【' + val.name + '】分配角色'
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
      this.$router.push({ path: '/systemMessage/adminUpdate', query: { id: this.selectId }})
    },
    viewHandler() {
      this.$router.push({ path: '/systemMessage/adminView', query: { id: this.selectId }})
    },
    setRolesHandler() {
      this.dialogVisible = true
      queryRoleByAll().then(response => {
        console.log(response.result)
      })
    },
    submitUserRolesHandler() {
    }
  }
}
</script>

<style scoped>
</style>

