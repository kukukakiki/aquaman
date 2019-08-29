<template>
  <div class="app-container">
    <el-form ref="queryForm" :inline="true" :model="query" class="demo-form-inline">
      <el-row>
        <el-col :span="8">
          <el-button-group>
            <el-button v-show_button="'adminAdd'" type="primary" @click.stop="addHandler">新增</el-button>
            <el-button v-show_button="'adminView'" :disabled="!showButton" type="primary" @click="viewHandler">查阅</el-button>
            <el-button v-show_button="'adminUpdate'" :disabled="!showButton" type="primary" @click.stop="updateHandler">修改</el-button>
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
          <span>{{ defaultFormateDate(scope.row.gmtCreate) }}</span>
        </template>
      </el-table-column>
    </el-table>
    <pagination :total="query.total" :page.sync="query.current" :limit.sync="query.size" @pagination="fetchData" />
  </div>
</template>

<script>
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
      query: { // 列表查询对象
        total: 0, // 总条数
        size: 5, // 每页条数
        current: 1 // 当前页码数
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
      this.loading = false
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
    addHandler() {
    },
    updateHandler() {
    },
    viewHandler() {
    }
  }
}
</script>

<style scoped>
</style>

