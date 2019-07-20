<template>
  <div class="app-container">
    <el-row>
      <el-col :span="6" style="padding-right: 10px;">
        <div style="padding-bottom: 5px;">
          <el-button-group>
            <el-button v-show_button="'deptAdd'" type="primary" @click.stop="addHandler">新增</el-button>
            <el-button v-show_button="'menuUpdate'" :disabled="!showButton" type="primary" @click.stop="updateHandler">修改</el-button>
            <el-button :disabled="!showButton" type="primary" @click.stop="deleteHandler">删除</el-button>
          </el-button-group>
        </div>
        <el-input v-model="filterText" placeholder="输入关键字进行过滤" />
        <el-tree ref="deptTree" :filter-node-method="filterNode" :data="deptTree" :props="defaultProps" class="filter-tree" default-expand-all @node-click="handleNodeClick" />
      </el-col>
      <el-col :span="18">
        <el-row>
          <el-col :span="24">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>菜单信息</span>
                <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
              </div>
              <div :key="o" class="text item">
                <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                  <el-form-item label="部门名称" prop="name">
                    <el-input v-model="form.name" />
                  </el-form-item>
                  <el-form-item label="部门类型" prop="type">
                    <aq-select :business-type="'deptType'" :bind-value.sync="form.type" style="width: 100%" />
                  </el-form-item>
                  <el-form-item label="部门地址" prop="address">
                    <el-input v-model="form.address" />
                  </el-form-item>
                  <el-form-item label="部门序号" prop="sort">
                    <el-input v-model="form.sort" />
                  </el-form-item>
                  <el-form-item label="上级部门" prop="parentName">
                    <el-select v-model="form.parentId" placeholder="请选择">
                      <el-option v-for="item in parentList" :key="item.id" :label="item.name" :value="item.id" />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="备注" prop="remarks">
                    <el-input v-model="form.remarks" type="textarea" />
                  </el-form-item>
                  <el-form-item label="部门状态" prop="status">
                    <aq-select :business-type="'status'" :bind-value.sync="form.status" style="width: 100%" />
                  </el-form-item>
                  <el-form-item>
                    <el-button v-if="showOperatorButton" type="primary" @click="submitHandler">{{ operatorButtonName }}</el-button>
                    <el-button v-if="showOperatorButton" @click="resetForm">重置</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </el-card>
          </el-col>
        </el-row>
        <el-row style="padding-top: 10px;">
          <el-col :span="24">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>部门人员</span>
                <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
              </div>
              <div :key="o" class="text item">
                <el-table :data="items" height="250" border style="width: 100%">
                  <el-table-column prop="id" label="序号" />
                  <el-table-column prop="name" label="名字" />
                  <el-table-column prop="mobil" label="手机" />
                  <el-table-column prop="email" label="邮箱" />
                </el-table>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { save, update } from '@/api/common'
import { queryAllByTree, queryById } from '@/api/dept'
import { isNotEmpty, resultValidate, resultSuccessShowMsg } from '@/utils/validate'
import aqSelect from '@/components/Element/Select'

export default {
  components: {
    aqSelect
  },
  data() {
    return {
      showOperatorButton: false, // 显示操作按钮
      operatorButtonName: '创建', // 操作按钮名称
      showButton: false,
      filterText: '', // 部门树搜索
      items: [], // 人员信息集合
      deptTree: [], // 部门树
      defaultProps: { // 部门树显示配置
        children: 'children',
        label: 'name'
      },
      parentList: [
        {
          id: -1,
          name: '根节点'
        }
      ],
      form: { // 部门表单属性
        id: '',
        name: '',
        type: '',
        status: '',
        address: '',
        remark: '',
        sort: '',
        parentId: '',
        parentName: '',
        permissionCode: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入部门名称', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择部门类型', trigger: 'change' }
        ],
        status: [
          { required: true, message: '请选择状态', trigger: 'change' }
        ]
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.deptTree.filter(val)
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      queryAllByTree().then(response => {
        this.deptTree = response.result
      })
    },
    filterNode(value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    },
    addHandler() {
      this.showOperatorButtonHandler()
      this.operatorButtonName = '创建'
      // 屏蔽修改，删除按钮
      this.showButton = false
      if (isNotEmpty(this.form.id)) {
        const parentId = this.form.id
        const name = this.form.name
        this.resetForm()
        this.form.parentId = parentId
        this.parentList = [{ id: parentId, name: name }]
      }
    },
    updateHandler() {
      this.showOperatorButtonHandler()
      this.operatorButtonName = '更新'
    },
    showOperatorButtonHandler() {
      this.showOperatorButton = true
    },
    handleNodeClick(data) {
      queryById(data.id).then(response => {
        const data = response.result
        if (resultValidate(response.code)) {
          this.form = data.dept
          if (this.form.parentId !== -1) {
            this.parentList = data.parentList
          } else {
            this.parentList = this.resetParentList()
          }
          this.showButton = true
        }
      })
    },
    submitHandler() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (isNotEmpty(this.form.id)) {
            update('dept', this.form).then(response => {
              resultSuccessShowMsg(response)
            })
          } else {
            save('dept', this.form).then(response => {
              resultSuccessShowMsg(response)
            })
          }
          this.fetchData()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm() {
      this.$refs['form'].resetFields()
      this.form.id = ''
      this.resetParentList()
    },
    resetParentList() {
      this.form.parentId = -1
      this.parentLeve = 1
      this.parentList = [{ id: -1, name: '根节点' }]
    }
  }
}
</script>

<style scoped>
</style>

