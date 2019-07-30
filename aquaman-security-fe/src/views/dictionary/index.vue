<template>
  <div class="app-container">
    <el-button-group style="padding-bottom: 10px;">
      <el-button v-show_button="'adminAdd'" type="primary" @click.stop="addGroupHandler">新增</el-button>
      <el-button v-show_button="'adminView'" :disabled="!showButton" type="primary" @click="viewHandler">查阅</el-button>
      <el-button v-show_button="'adminUpdate'" :disabled="!showButton" type="primary" @click.stop="updateHandler">修改</el-button>
    </el-button-group>
    <el-row>
      <!-- 字典组列表 -->
      <el-col :span="12" style="padding-right: 10px;">
        <el-card shadow="always" style="height: 500px;">
          <div slot="header">
            <span>字典组</span>
          </div>
          <el-table v-loading="loading" :data="groupItems" border style="width: 100%" highlight-current-row @current-change="handleGroupCurrentChange">
            <el-table-column prop="name" label="名称" />
            <el-table-column prop="code" label="编码" />
            <el-table-column prop="type" label="类型" width="70" />
            <el-table-column prop="status" label="状态" width="50" />
            <!-- <el-table-column prop="gmtCreate" label="创建时间">
              <template slot-scope="scope">
                <i class="el-icon-time" />
                <span>{{ formateDate(scope.row.gmtCreate) }}</span>
              </template>
            </el-table-column> -->
            <el-table-column fixed="right" label="操作" width="100">
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="addItemHandler(scope.row)">新增字典项</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
        <pagination :total="query.total" :page.sync="query.current" :limit.sync="query.size" @pagination="fetchData" />
      </el-col>
      <!-- 字典项列表 -->
      <el-col :span="12">
        <el-card shadow="always" style="height: 500px;">
          <div slot="header">
            <span>字典项</span>
          </div>
          <!-- @current-change="handleGroupCurrentChange" -->
          <el-table v-loading="loading" :data="items" border style="width: 100%" highlight-current-row>
            <el-table-column prop="name" label="名称" />
            <el-table-column prop="code" label="编码" />
            <el-table-column prop="value" label="数值" />
            <el-table-column prop="status" label="状态" width="50" />
            <!-- <el-table-column prop="gmtCreate" label="创建时间">
              <template slot-scope="scope">
                <i class="el-icon-time" />
                <span>{{ formateDate(scope.row.gmtCreate) }}</span>
              </template>
            </el-table-column> -->
            <el-table-column fixed="right" label="操作" width="130">
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="addItemHandle(scope.row)">查阅</el-button>
                <el-button type="text" size="small" @click="addItemHandle(scope.row)">修改</el-button>
                <el-button type="text" size="small" @click="addItemHandle(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
        <pagination :total="itemQuery.total" :page.sync="itemQuery.current" :limit.sync="itemQuery.size" @pagination="fetchItemData" />
      </el-col>
    </el-row>
    <!-- 字典组CRU弹窗 -->
    <el-dialog
      :visible.sync="dialogGroupVisible"
      title="字典组"
      width="70%">
      <el-form ref="form" :rules="groupRules" :model="groupForm" label-width="120px">
        <el-row class="my_row">
          <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
            <el-form-item label="编码" prop="code">
              <el-input v-model="groupForm.code" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
            <el-form-item label="名称" prop="name">
              <el-input v-model="groupForm.name" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
            <el-form-item label="类型" prop="type">
              <el-input v-model="groupForm.type" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
            <el-form-item label="状态" prop="status">
              <aq-select :business-type="'status'" :bind-value.sync="groupForm.status" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row class="my_row">
          <el-col :span="24">
            <el-form-item label="备注" prop="remarks">
              <el-input v-model="groupForm.remarks" type="textarea" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogGroupVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitGroupHandler">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 字典项CRU弹窗 -->
    <el-dialog
      :visible.sync="dialogItemVisible"
      title="字典项"
      width="70%">
      <el-form ref="form" :rules="itemRules" :model="itemForm" label-width="120px">
        <el-row class="my_row">
          <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
            <el-form-item label="编码" prop="code">
              <el-input v-model="itemForm.code" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
            <el-form-item label="名称" prop="name">
              <el-input v-model="itemForm.name" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
            <el-form-item label="数值" prop="value">
              <el-input v-model="itemForm.value" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
            <el-form-item label="状态" prop="status">
              <aq-select :business-type="'status'" :bind-value.sync="itemForm.status" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row class="my_row">
          <el-col :span="24">
            <el-form-item label="备注" prop="remarks">
              <el-input v-model="itemForm.remarks" type="textarea" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogItemVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitItemHandler">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { queryByPage, save } from '@/api/common'
import aqSelect from '@/components/Element/Select'
import Pagination from '@/components/Pagination'
import CheckBoxRole from '@/components/Business/Role/CheckBoxRole'
import { resultValidate, resultSuccessShowMsg } from '@/utils/validate'

export default {
  components: {
    Pagination,
    CheckBoxRole,
    aqSelect
  },
  data() {
    return {
      hiddenQuery: false,
      showButton: false, // 显示执行按钮
      loading: false, // 页面loading标示
      submitLoading: false,
      dialogGroupVisible: false,
      dialogItemVisible: false,
      groupItems: [], // 列表集合
      query: { // 列表查询对象
        total: 0, // 总条数
        size: 5, // 每页条数
        current: 1 // 当前页码数
      },
      groupForm: {
        id: '',
        code: '',
        name: '',
        type: '',
        status: '',
        remark: ''
      },
      groupRules: {
        code: [
          { required: true, message: '请输入字典组编码', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入字典组名称', trigger: 'blur' }
        ]
      },
      items: [], // 列表集合,
      itemQuery: { // 列表查询对象
        groupId: '',
        total: 0, // 总条数
        size: 5, // 每页条数
        current: 1 // 当前页码数
      },
      itemForm: {
        id: '',
        groupId: '',
        code: '',
        name: '',
        value: '',
        status: '',
        remark: ''
      },
      itemRules: {
        code: [
          { required: true, message: '请输入字典项编码', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入字典项名称', trigger: 'blur' }
        ],
        value: [
          { required: true, message: '请输入字典项数值', trigger: 'blur' }
        ]
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
      this.loading = true
      queryByPage('dictionary_group', this.query).then(response => {
        const data = response.result
        if (resultValidate(response.code)) {
          this.groupItems = data.records
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
    fetchItemData() {
      queryByPage('dictionary_item', this.itemQuery).then(response => {
        const data = response.result
        if (resultValidate(response.code)) {
          this.items = data.records
          this.itemQuery.total = data.total
          this.itemQuery.size = data.size
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
    handleGroupCurrentChange(val) {
      if (val) {
        this.showButton = true
        this.selectId = val.id
        // 查询该字典组所有字典项的集合
        this.itemQuery.groupId = val.id
        this.fetchItemData()
      } else {
        this.showButton = false
      }
    },
    handleItemCurrentChange(val) {
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
    addGroupHandler() {
      this.dialogGroupVisible = true
    },
    addItemHandler(row) {
      console.log(row)
      this.itemForm.groupId = row.id
      this.dialogItemVisible = true
    },
    updateHandler() {
    },
    viewHandler() {
    },
    submitGroupHandler() {
      this.$refs['form'].validate(validate => {
        if (validate) {
          this.submitLoading = true
          save('dictionary_group', this.groupForm).then(response => {
            this.submitLoading = false
            resultSuccessShowMsg(response)
            this.dialogGroupVisible = false
            this.fetchData()
          }).catch(error => {
            this.submitLoading = false
            console.log(error)
          })
        }
      })
    },
    submitItemHandler() {
      this.$refs['form'].validate(validate => {
        if (validate) {
          this.submitLoading = true
          save('dictionary_item', this.itemForm).then(response => {
            this.submitLoading = false
            resultSuccessShowMsg(response)
            this.dialogItemVisible = false
            this.fetchItemData()
          }).catch(error => {
            this.submitLoading = false
            console.log(error)
          })
        }
      })
    }
  }
}
</script>

<style scoped>
</style>

