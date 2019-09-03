<template>
  <div class="app-container">
    <el-button-group style="padding-bottom: 10px;">
      <el-button v-show_button="'adminAdd'" type="primary" @click.stop="addGroupHandler">新增</el-button>
      <el-button v-show_button="'adminUpdate'" :disabled="!showButton" type="primary" @click.stop="updateGroupHandler">修改</el-button>
      <!--  v-show_button="'adminDelete'" -->
      <el-button :disabled="!showButton" type="primary" @click.stop="deletedGroupHandler">删除</el-button>
      <!-- <el-button v-show_button="'adminView'" :disabled="!showButton" type="primary" @click="viewHandler">查阅</el-button> -->
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
            <el-table-column prop="status" label="状态" width="50" >
              <template slot-scope="scope">
                {{ getLabel('status', scope.row.status) }}
              </template>
            </el-table-column>
            <!-- <el-table-column prop="gmtCreate" label="创建时间">
              <template slot-scope="scope">
                <i class="el-icon-time" />
                <span>{{ defaultFormateDate(scope.row.gmtCreate) }}</span>
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
          <el-table v-loading="loading" :data="items" border style="width: 100%" highlight-current-row @current-change="handleItemCurrentChange">
            <el-table-column prop="name" label="名称" />
            <el-table-column prop="code" label="编码" />
            <el-table-column prop="value" label="数值" />
            <el-table-column prop="status" label="状态" width="50" >
              <template slot-scope="scope">
                {{ getLabel('status', scope.row.status) }}
              </template>
            </el-table-column>
            <!-- <el-table-column prop="gmtCreate" label="创建时间">
              <template slot-scope="scope">
                <i class="el-icon-time" />
                <span>{{ formateDate(scope.row.gmtCreate) }}</span>
              </template>
            </el-table-column> -->
            <el-table-column fixed="right" label="操作" width="130">
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="viewItemHandler(scope.row)">查阅</el-button>
                <!-- <el-button type="text" size="small" @click="updateItemHandler(scope.row)">修改</el-button> -->
                <el-button type="text" size="small" @click="deleteItemHandler(scope.row)">删除</el-button>
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
      <el-form ref="groupForm" :rules="groupRules" :model="groupForm" label-width="120px">
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
      <el-form ref="itemForm" :rules="itemRules" :model="itemForm" label-width="120px">
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
import { queryByPage, save, queryById, update, deleted } from '@/api/common'
import aqSelect from '@/components/Element/Select'
import Pagination from '@/components/Pagination'
import CheckBoxRole from '@/components/Business/Role/CheckBoxRole'
import { resultValidate, resultSuccessShowMsg } from '@/utils/validate'
import { getLabel } from '@/utils/constant'

export default {
  components: {
    Pagination,
    CheckBoxRole,
    aqSelect
  },
  data() {
    return {
      selectId: null,
      selectItemId: null,
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
        remarks: ''
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
        remarks: ''
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
    getLabel,
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
          this.items = []
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
    fetchGroupDataById() {
      queryById('dictionary_group', this.selectId).then(response => {
        this.groupForm = response.result
      }).catch(error => {
        this.$message({
          message: error.msg,
          type: 'error',
          duration: 5 * 1000
        })
        this.loading = false
      })
    },
    fetchItemDataById() {
      queryById('dictionary_item', this.selectItemId).then(response => {
        this.itemForm = response.result
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
        this.fetchGroupDataById()
        // 查询该字典组所有字典项的集合
        this.itemQuery.groupId = val.id
        this.fetchItemData()
      } else {
        this.showButton = false
      }
    },
    handleItemCurrentChange(val) {
      this.selectItemId = val.id
      this.fetchItemDataById()
    },
    addGroupHandler() {
      this.resetGroupFormFiedlds()
      this.loggerInfo('控制台输出：', 'dsadasd电视剧啊看得开', this.selectId)
      this.dialogGroupVisible = true
    },
    resetGroupFormFiedlds() {
      this.groupForm.id = ''
      this.groupForm.code = ''
      this.groupForm.name = ''
      this.groupForm.type = ''
      this.groupForm.status = ''
      this.groupForm.remarks = ''
    },
    updateGroupHandler() {
      this.fetchGroupDataById()
      this.dialogGroupVisible = true
    },
    viewHandler() {
    },
    deletedGroupHandler() {
      this.loggerInfo('控制台输出：', 'dsadasd电视剧啊看得开', this.selectId)
      if (this.selectId) {
        deleted('dictionary_group', this.selectId).then(response => {
          this.submitLoading = false
          resultSuccessShowMsg(response)
          this.dialogGroupVisible = false
          this.fetchData()
        }).catch(error => {
          this.submitLoading = false
          console.log(error)
        })
      } else {
        this.$message({
          message: '请选择一行字典项删除',
          type: 'error',
          duration: 5 * 1000
        })
      }
    },
    submitGroupHandler() {
      this.$refs['groupForm'].validate(validate => {
        if (validate) {
          this.submitLoading = true
          if (this.groupForm.id) {
            update('dictionary_group', this.groupForm).then(response => {
              this.submitLoading = false
              resultSuccessShowMsg(response)
              this.dialogGroupVisible = false
              this.fetchData()
            }).catch(error => {
              this.submitLoading = false
              console.log(error)
            })
          } else {
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
        }
      })
    },
    addItemHandler(row) {
      this.resetItemFormFiedlds()
      this.itemForm.groupId = row.id
      this.dialogItemVisible = true
    },
    resetItemFormFiedlds() {
      this.itemForm.id = ''
      this.itemForm.code = ''
      this.itemForm.groupId = ''
      this.itemForm.name = ''
      this.itemForm.value = ''
      this.itemForm.status = ''
      this.itemForm.remarks = ''
    },
    submitItemHandler() {
      this.$refs['itemForm'].validate(validate => {
        if (validate) {
          this.submitLoading = true
          if (this.itemForm.id) {
            update('dictionary_item', this.itemForm).then(response => {
              this.submitLoading = false
              resultSuccessShowMsg(response)
              this.dialogItemVisible = false
              this.fetchItemData()
            }).catch(error => {
              this.submitLoading = false
              console.log(error)
            })
          } else {
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
        }
      })
    },
    viewItemHandler() {
    },
    updateItemHandler() {
      this.dialogItemVisible = true
    },
    deleteItemHandler() {
    }
  }
}
</script>

<style scoped>
</style>

