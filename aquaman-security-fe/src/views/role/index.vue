<template>
  <div class="app-container">
    <el-form ref="queryForm" :inline="true" :model="query" class="demo-form-inline">
      <el-row>
        <el-col :span="8">
          <el-button-group>
            <el-button v-show_button="'roleAdd'" type="primary" @click.stop="addHandler">新增</el-button>
            <el-button v-show_button="'roleView'" :disabled="!showButton" type="primary" @click.stop="viewHandler">查阅</el-button>
            <el-button v-show_button="'roleUpdate'" :disabled="!showButton" type="primary" @click.stop="updateHandler">修改</el-button>
            <el-button v-show_button="'roleAuthorization'" :disabled="!showButton" type="primary" @click.stop="authoHandler">授权</el-button>
            <el-button :disabled="!showButton" type="primary" @click.stop="deleteHandler">删除</el-button>
          </el-button-group>
        </el-col>
        <el-col :span="16">
          <el-button-group style="float:right">
            <el-button type="primary" icon="el-icon-search" title="查询列表" @click="fetchData" />
            <el-button type="primary" icon="el-icon-more" title="更多条件" @click="showMoreQuery" />
            <el-button type="primary" icon="el-icon-refresh" title="清空条件" @click="resetQuery" />
          </el-button-group>
          <el-form-item label="角色编码" style="float:right" prop="code">
            <el-input v-model="query.code" placeholder="角色编码" />
          </el-form-item>
        </el-col>
      </el-row>
      <transition name="slide-fade">
        <div v-if="hiddenQuery">
          <el-row class="my_row">
            <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
              <el-form-item label="角色名称" prop="name">
                <el-input v-model="query.name" placeholder="角色名称" />
              </el-form-item>
            </el-col>
          </el-row>
        </div>
      </transition>
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

    <!-- 角色授权Dialog -->
    <el-dialog
      :visible.sync="dialogVisible"
      :title="setRoleMenuTitle"
      width="50%">
      <menu-tree ref="menuTree" :keys.sync="roleMenuIds" />
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="setRoleMenuHandler">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { queryByPage, update } from '@/api/common'
import { queryRoleMenuDetailByRoleId } from '@/api/role'
import Pagination from '@/components/Pagination'
import menuTree from '@/components/Business/MenuTree'
import { resultSuccessShowMsg } from '@/utils/validate'

export default {
  components: {
    Pagination,
    menuTree
  },
  data() {
    return {
      roleMenuIds: [],
      setRoleMenuTitle: '',
      showButton: false, // 显示执行按钮
      hiddenQuery: false,
      items: [], // 列表集合
      selectId: '', // 选中ID
      dialogVisible: false,
      query: { // 列表查询对象
        total: 0, // 总条数
        size: 5, // 每页条数
        current: 1, // 当前页码数
        account: '', // 用户名
        status: '' // 用户状态
      },
      roleMenu: {
        id: '',
        roleId: '',
        menuIds: ''
      }
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
        this.setRoleMenuTitle = '【' + val.name + '】角色菜单授权'
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
    },
    viewHandler() {
      this.$router.push({
        path: '/systemMessage/roleView',
        query: { id: this.selectId }
      })
    },
    authoHandler() {
      this.roleMenuIds = []
      this.dialogVisible = true
      queryRoleMenuDetailByRoleId(this.selectId).then(response => {
        this.roleMenu = response.result
        if (response.result.menuIds !== undefined && response.result.menuIds !== null) {
          this.roleMenuIds = response.result.menuIds.split(',')
        }
      }).catch(error => {
        console.log(error)
      })
    },
    setRoleMenuHandler() {
      this.roleMenu.menuIds = this.roleMenuIds.join(',')
      update('role_menu', this.roleMenu).then(response => {
        resultSuccessShowMsg(response)
        if (response.code === '0000') {
          this.dialogVisible = false
        }
      }).catch(error => {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>
</style>

