<template>
  <div class="app-container">
    <el-row>
      <el-col :span="6" style="padding-right: 10px;">
        <el-input v-model="filterText" placeholder="输入关键字进行过滤" />
        <el-tree ref="deptTree" :filter-node-method="filterNode" :data="deptTree" :props="defaultProps" class="filter-tree" default-expand-all />
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
                <el-form ref="form" :model="form" label-width="80px">
                  <el-form-item label="部门名称">
                    <el-input v-model="form.name" />
                  </el-form-item>
                  <el-form-item label="部门类型">
                    <el-input v-model="form.type" />
                  </el-form-item>
                  <el-form-item label="部门状态">
                    <el-input v-model="form.status" />
                  </el-form-item>
                  <el-form-item label="部门地址">
                    <el-input v-model="form.address" />
                  </el-form-item>
                  <el-form-item label="部门序号">
                    <el-input v-model="form.sort" />
                  </el-form-item>
                  <el-form-item label="上级部门">
                    <el-input v-model="form.parentName" />
                  </el-form-item>
                  <el-form-item label="备注">
                    <el-input v-model="form.remark" />
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
import { queryAllByTree } from '@/api/dept'

export default {
  components: {
  },
  data() {
    return {
      showOperatorButton: false, // 显示操作按钮
      operatorButtonName: '创建', // 操作按钮名称
      filterText: '', // 部门树搜索
      items: [], // 人员信息集合
      deptTree: [], // 部门树
      defaultProps: { // 部门树显示配置
        children: 'children',
        label: 'name'
      },
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
    submitHandler() {},
    resetForm() {}
  }
}
</script>

<style scoped>
</style>

