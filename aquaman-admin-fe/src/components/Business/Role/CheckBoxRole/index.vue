<template>
  <div class="app-container">
    <el-form ref="queryForm" :inline="true" :model="query" class="demo-form-inline">
      <el-row>
        <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
          <el-form-item label="角色编码" prop="code">
            <el-input v-model="query.code" placeholder="角色编码" />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
          <el-form-item label="角色名称" prop="name">
            <el-input v-model="query.name" placeholder="角色名称" />
          </el-form-item>
        </el-col>
        <el-button-group>
          <el-button type="primary" title="查询列表" @click="fetchData">查询</el-button>
          <el-button type="primary" title="清空条件" @click="resetQuery">重置</el-button>
        </el-button-group>
      </el-row>
    </el-form>
    <el-table ref="multipleTable" :data="items" row-key="id" border style="width: 100%" highlight-current-row @select="selectHandler" @select-all="selectAllHandler">
      <el-table-column :reserve-selection="true" type="selection" width="55" />
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
  props: {
    keys: {
      required: true,
      type: Array
    }
  },
  data() {
    return {
      items: [], // 列表集合
      selectKeys: this.keys, // 选中对象
      query: { // 列表查询对象
        total: 0, // 总条数
        size: 5, // 每页条数
        current: 1, // 当前页码数
        account: '', // 用户名
        status: 'start' // 用户状态
      }
    }
  },
  watch: {
    keys(val) {
      this.selectKeys = val
    },
    selectKeys(val) {
      this.$emit('update:keys', val)
    }
  },
  created() {
    this.fetchData()
  },
  mounted() {
    if (this.selectKeys) {
      this.selectKeys.forEach(row => {
        this.$refs.multipleTable.toggleRowSelection(row)
      })
    }
  },
  methods: {
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
    selectHandler(selection, row) {
      this.selectKeys = selection
    },
    selectAllHandler(selection) {
      this.selectKeys = selection
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

